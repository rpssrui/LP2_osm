package edu.ufp.inf.project;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date implements Comparable<Date>, Serializable {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    public Date(int year, int month, int day, int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public Date(Date d) {
        this.year = d.year;
        this.month = d.month;
        this.day = d.day;
        this.hour = d.hour;
        this.minute = d.minute;
        this.second = d.second;
    }

    public Date() {
        GregorianCalendar g = new GregorianCalendar();
        this.year = g.get(Calendar.YEAR);
        this.month = g.get(Calendar.MONTH) + 1;
        this.day = g.get(Calendar.DAY_OF_MONTH);
        this.hour = g.get(Calendar.HOUR_OF_DAY);
        this.minute = g.get(Calendar.MINUTE);
        this.second = g.get(Calendar.SECOND);
    }


    public boolean beforeDate(Date d) {
        return this.compareTo(d) < 0;
    }

    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));

    }

    public boolean afterDate(Date d) {
        return this.compareTo(d) > 0;
    }

    public void incrementDate() {
        if (daysMonth((short) this.month, this.year) == this.day) {
            if (this.month == 12) {
                //last day of the year
                this.year++;
                this.month = 1;
                this.day = 1;
                return;
            }
            //last day of the month
            this.month++;
            this.day = 1;
            return;
        }
        //any other day
        this.day++;
    }

    @Override
    public int compareTo(Date d) {

        if (this.day == d.day && this.month == d.month && this.year == d.year && this.hour == d.hour && this.minute == d.minute && this.second == d.second)
            return 0;

        if (this.year < d.year)
            return -1;
        if (this.year == d.year) {
            if (this.month < d.month)
                return -1;
            if (this.month == d.month) {
                if (this.day < d.day)
                    return -1;
                if (this.day == d.day) {
                    if (this.hour < d.hour)
                        return -1;
                    if (this.hour == d.hour) {
                        if (this.minute < d.minute)
                            return -1;
                        if (this.minute == d.minute) {
                            if (this.second < d.second)
                                return -1;
                        }
                    }
                }
            }
        }

        return 1;
    }

    public static int daysMonth(short month, int year) {
        switch (month) {
            case 11:
            case 4:
            case 6:
            case 9:
                return 30;
            case 2:
                return (Date.isLeapYear(year)) ? 29 : 28;
            default:
                return 31;
        }

    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return this.year + "/" + this.month + "/" + this.day + " (" + this.hour + ":" + this.minute + ":" + this.second + ")";
    }

    public static int daysBetweenDates(Date begin, Date end) {
        if (begin.afterDate(end))
            return -1;

        if (begin.year == end.year)
            return Date.daysBetweenMonths(begin.day, begin.month, end.day, end.month, end.year);


        Date aux = new Date(begin);
        int days = Date.daysBetweenMonths(aux.day, aux.month, 31, 12, end.year);
        while (++aux.year < end.year)
            days += (isLeapYear(aux.year) ? 366 : 365);

        return days + Date.daysBetweenMonths(1, 1, end.day, end.month, end.year);

    }

    private static int daysBetweenMonths(int beginDay, int beginMonth, int endDay, int endMonth, int year) {
        if (beginMonth == endMonth)
            return endDay - beginDay;

        int days = Date.daysMonth((short) beginMonth, year) - beginDay;
        while (++beginMonth < endMonth)
            days += Date.daysMonth((short) beginMonth, year);

        return days + endDay;
    }


    private static int daysBetweenDatesRecursiveAux(Date begin, Date end) {
        if (begin.beforeDate(end)) {
            begin.incrementDate();
            return 1 + daysBetweenDatesRecursiveAux(begin, end);
        }
        return 0;
    }

    private static int daysBetweenDatesRecursive(Date begin, Date end) {
        Date auxBegin = new Date(begin);
        int n_years = 10;
        int days = 0;
        Date auxEnd = new Date(auxBegin.day, auxBegin.month, auxBegin.year + n_years + 1);

        while (auxEnd.year < end.year) {

            days += Date.daysBetweenDatesRecursiveAux(auxBegin, auxEnd);
            auxEnd = new Date(auxBegin.day, auxBegin.month, auxBegin.year + n_years + 1);

        }
        days += Date.daysBetweenDatesRecursiveAux(auxBegin, end);
        return days;
    }

}
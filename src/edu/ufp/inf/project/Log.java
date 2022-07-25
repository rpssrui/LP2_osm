package edu.ufp.inf.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;


public class Log implements Serializable {

    public Date date;
    public String message;
    public static ArrayList<Log> logsGlobal = new ArrayList<>();

    public Log(Date date, String message) {
        this.date = date;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Log{" +
                "date=" + date +
                ", message='" + message + '\'' +
                '}';
    }
}
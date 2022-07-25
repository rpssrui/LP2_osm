package edu.ufp.inf.project;

import edu.princeton.cs.algs4.ST;

import java.io.Serializable;
import java.util.Vector;

public class Location implements Serializable {

    private String local;
    private double latitude;
    private double longitude;
    public static ST<String, Integer> localsST = new ST<>();

    public Location(String local, double latitude, double longitude) {
        this.local = local;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLocal() {
        return local;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Location{" +
                "local='" + local + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
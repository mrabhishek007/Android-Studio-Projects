package com.example.android.quakereport;

/**
 * Created by Vikash on 1/3/2018.
 */

public class MyEarthquakeHandler
{
    String magnitude,exact_location,country,time;

    public MyEarthquakeHandler(String magnitude, String exact_location, String country, String time)
    {
        this.magnitude = magnitude;
        this.exact_location = exact_location;
        this.country = country;
        this.time = time;
    }

    public String getMagnitude()
    {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getExact_location() {
        return exact_location;
    }

    public void setExact_location(String exact_location) {
        this.exact_location = exact_location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

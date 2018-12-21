package com.example.vikash.darkskyclient.events;

import model.Weather;

public class WeatherEvent {

    Weather weather;

    public WeatherEvent(Weather weather) {
        this.weather = weather ;
    }

    public Weather getWeather() {
        return weather;
    }
}

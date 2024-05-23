package com.example.demo.main.fourth.androidcommunication.utils;

/**
 * Author: Eccentric
 * Created on 2024/5/22 14:21.
 * Description: com.example.sanfangcommuinate.rxbus.WeatherEvent
 */
public class WeatherEvent {
    private String id;
    private String cityName;
    private String temperature;

    public WeatherEvent(String id, String cityName, String temperature) {
        this.id = id;
        this.cityName = cityName;
        this.temperature = temperature;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "WeatherEvent{" +
                "id='" + id + '\'' +
                ", cityName='" + cityName + '\'' +
                ", temperature='" + temperature + '\'' +
                '}';
    }
}

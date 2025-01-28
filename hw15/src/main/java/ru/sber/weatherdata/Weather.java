package ru.sber.weatherdata;

import lombok.Getter;

@Getter
public class Weather {
    private Current current;
    private Location location;
}

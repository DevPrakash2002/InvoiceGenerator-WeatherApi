package com.freight.fox.model;

import lombok.Data;
import java.util.List;

@Data
public class OpenWeatherResponse {
    private Main main;
    private List<Weather> weather;

    @Data
    public static class Main {
        private double temp;
        private int humidity;
    }

    @Data
    public static class Weather {
        private String description;
    }
}
package com.freight.fox.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class WeatherResponse {
    private double temperature;
    private int humidity;
    private String description;
    private LocalDate date;
    private String pincode;
}

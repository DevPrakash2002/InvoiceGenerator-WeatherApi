package com.freight.fox.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class WeatherRequest {
    private String pincode;
    private LocalDate date;
}
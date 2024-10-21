package com.freight.fox.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freight.fox.model.WeatherRequest;
import com.freight.fox.model.WeatherResponse;
import com.freight.fox.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
@Tag(name = "Weather Controller", description = "Weather information endpoints")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @PostMapping
    @Operation(
        summary = "Get weather information",
        description = "Get weather details for a specific pincode and date"
    )
    @ApiResponse(responseCode = "200", description = "Weather information retrieved successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input parameters")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<WeatherResponse> getWeather(@Valid @RequestBody WeatherRequest request) {
        return ResponseEntity.ok(weatherService.getWeatherInfo(request));
    }
}


package com.freight.fox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.freight.fox.model.GeocodeResponse;
import com.freight.fox.model.GeocodeResult;
import com.freight.fox.model.OpenWeatherResponse;
import com.freight.fox.model.WeatherRequest;
import com.freight.fox.model.WeatherResponse;

@Service
public class WeatherService {

    @Value("${geoapify.api.key}")
    private String geoapifyApiKey;

    @Value("${openweathermap.api.key}")
    private String weatherApiKey;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeatherInfo(WeatherRequest request) {
        // First get coordinates from pincode
        String geocodeUrl = String.format(
            "https://api.geoapify.com/v1/geocode/search?postcode=%s&format=json&apiKey=%s",
            request.getPincode(), geoapifyApiKey
        );
        
        GeocodeResponse geocodeResponse = restTemplate.getForObject(geocodeUrl, GeocodeResponse.class);
        
        if (geocodeResponse == null || geocodeResponse.getResults().isEmpty()) {
            throw new RuntimeException("Unable to find coordinates for given pincode");
        }

        GeocodeResult location = geocodeResponse.getResults().get(0);
        
        // Now get weather data using coordinates
        String weatherUrl = String.format(
            "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s",
            location.getLat(), location.getLon(), weatherApiKey
        );
        
        OpenWeatherResponse weatherResponse = restTemplate.getForObject(weatherUrl, OpenWeatherResponse.class);
        
        return WeatherResponse.builder()
            .temperature(weatherResponse.getMain().getTemp())
            .humidity(weatherResponse.getMain().getHumidity())
            .description(weatherResponse.getWeather().get(0).getDescription())
            .date(request.getDate())
            .pincode(request.getPincode())
            .build();
    }
}

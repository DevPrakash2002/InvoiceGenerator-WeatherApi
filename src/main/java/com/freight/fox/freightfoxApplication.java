package com.freight.fox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class freightfoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(freightfoxApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
    public OpenAPI weatherOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Weather API")
                .description("API to get weather information by pincode and date")
                .version("1.0"));
    }

}

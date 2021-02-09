package com.assignment.spring.controller;

import com.assignment.spring.domain.WeatherEntity;
import com.assignment.spring.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/api/v1//weather")
    public ResponseEntity<WeatherEntity> getWeather(@RequestParam("city") String city) {

        log.info("REST request to /api/v1//weather?city={}", city);
        ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.OK).body(weatherService.getWeatherByCityName(city));
        log.info("/api/v1//weather?city={} : {}", city,responseEntity);
        return responseEntity;
    }



}

package com.assignment.spring.service;

import com.assignment.spring.domain.WeatherEntity;
import com.assignment.spring.repository.WeatherRepository;
import com.assignment.spring.api.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ehsan Sh
 */


@Service
@Slf4j
public class WeatherService {

    @Value("${openWeather.appId}")
    private String appId ;

    @Value("${openWeather.url}")
    private String weatherApiUrl;

    private final RestTemplate restTemplate;

    private final WeatherRepository weatherRepository;

    public WeatherService(RestTemplate restTemplate, WeatherRepository weatherRepository) {
        this.restTemplate = restTemplate;
        this.weatherRepository = weatherRepository;
    }

    public WeatherEntity getWeatherByCityName(String city){
        String url =weatherApiUrl.replace("{city}", city).replace("{appId}", appId);
        log.info("openWeather url:{} ",url );
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);
        log.info("openWeather  response : {}", response);

        return weatherRepository.save(WeatherMapper.mapper(response.getBody()));
    }




    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setWeatherApiUrl(String weatherApiUrl) {
        this.weatherApiUrl = weatherApiUrl;
    }
}   

package com.assignment.spring;

import com.assignment.spring.domain.WeatherEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ehsan Sh
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void getWeather() throws Exception {
        //given
        String cityName= "tehran";

        //then
        ResponseEntity<WeatherEntity> responseEntity = restTemplate.getForEntity("/api/v1//weather?city=" + cityName, WeatherEntity.class);

        //expect
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseEntity.getBody().getCountry(),"IR");

    }
}   

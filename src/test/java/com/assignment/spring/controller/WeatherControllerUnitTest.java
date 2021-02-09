package com.assignment.spring.controller;

import com.assignment.spring.domain.WeatherEntity;
import com.assignment.spring.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ehsan Sh
 */


@WebMvcTest(WeatherController.class)
@AutoConfigureMockMvc
public class WeatherControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WeatherService weatherService;


    @Test
    void getWeather() throws Exception {
        //given
        String cityName= "tehran";

        WeatherEntity weatherEntity = WeatherEntity.builder()
                .id(1)
                .city(cityName)
                .country("IR")
                .temperature(272.14)
                .build();

        when(weatherService.getWeatherByCityName(eq(cityName))).thenReturn(weatherEntity);

        //expect
        mockMvc.perform(get("/api/v1//weather?city=" + cityName))
                .andExpect(status().isOk());

    }

    @Test
    void getWeather_BadRequest() throws Exception {
        //given
        String cityName= "tehran";


        //expect
        mockMvc.perform(get("/api/v1//weather?citi=" + cityName))
                .andExpect(status().isBadRequest());

    }



}


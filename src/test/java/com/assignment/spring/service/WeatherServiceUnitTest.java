package com.assignment.spring.service;

import com.assignment.spring.api.Main;
import com.assignment.spring.api.Sys;
import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.domain.WeatherEntity;
import com.assignment.spring.repository.WeatherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.Charset;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

/**
 * @author Ehsan Sh
 */

@ExtendWith(MockitoExtension.class)
public class WeatherServiceUnitTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private WeatherService weatherService;

    @BeforeEach
    public void setupWeatherService() {
        weatherService.setAppId("921070dad9367e4b34b002eea4c1b557");
        weatherService.setWeatherApiUrl("http://api.openweathermap.org/data/2.5/weather?q={city}&APPID={appId}");


    }

    @Test
    public void testGetWeather_Success() throws Exception {
        //given
        WeatherResponse response = new WeatherResponse();
        response.setName("londen");
        Sys sys = new Sys();
        sys.setCountry("BG");
        response.setSys(sys);
        Main main = new Main();
        main.setTemp(272.38);
        response.setMain(main);
        when(restTemplate.getForEntity(
                eq("http://api.openweathermap.org/data/2.5/weather?q=londen&APPID=921070dad9367e4b34b002eea4c1b557"),
                eq(WeatherResponse.class)))
                .thenReturn(ResponseEntity.status(HttpStatus.OK).body(response));

        WeatherEntity weatherEntity = WeatherEntity.builder().id(null).temperature(272.38).country("BG").city("londen").build();
        given(weatherRepository.save(weatherEntity)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));


        //then
        WeatherEntity res = weatherService.getWeatherByCityName("londen");

        //expect
        assertNotNull(res);
        assertTrue(res.getCountry().equals("BG"));
    }


    @Test
    public void testGetWeather_withWrongCityName() throws Exception {

        //given
        HttpClientErrorException exception = new HttpClientErrorException(HttpStatus.NOT_FOUND,
                "Not Found", "[{\"cod\":\"404\",\"message\":\"city not found\"}]".getBytes(), Charset.defaultCharset());

        when(restTemplate.getForEntity(
                eq("http://api.openweathermap.org/data/2.5/weather?q=londen1&APPID=921070dad9367e4b34b002eea4c1b557"),
                eq(WeatherResponse.class)))
                .thenThrow(exception);

        //expect
        try {

            weatherService.getWeatherByCityName("londen1");
        } catch (HttpClientErrorException ex) {
            assertEquals(ex.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}   

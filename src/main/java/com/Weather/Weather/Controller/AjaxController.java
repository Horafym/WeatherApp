package com.Weather.Weather.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Weather.Weather.Service.WeatherService;

@RestController
public class AjaxController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("/getWeather")
    public void test() {
        weatherService.getWeatherFor("11.5892", "50.9271");
    }

}

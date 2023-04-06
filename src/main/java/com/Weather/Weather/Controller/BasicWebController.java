package com.Weather.Weather.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.Weather.Weather.Model.WeatherResponse;
import com.Weather.Weather.Service.WeatherService;

@RestController
public class BasicWebController {

    @Autowired
    WeatherService weatherService;

    @RequestMapping("/")
    public ModelAndView showIndex(@RequestParam(name = "location", defaultValue = "Jena") String location) {
        final ModelAndView modelAndView = new ModelAndView("index");

        WeatherResponse weatherResponse = weatherService.getWeatherFor(location);
        LocalDateTime localDateTime = LocalDateTime.now();

        modelAndView.addObject("weatherResponse", weatherResponse);
        modelAndView.addObject("dayOfWeek", localDateTime.getDayOfWeek().getDisplayName(TextStyle.FULL,
                Locale.ENGLISH));
        modelAndView.addObject("month", localDateTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
        modelAndView.addObject("dayOfMonth", localDateTime.getDayOfMonth());

        modelAndView.addObject("time", DateTimeFormatter.ofPattern("HH:mm").format(localDateTime));

        return modelAndView;
    }

}
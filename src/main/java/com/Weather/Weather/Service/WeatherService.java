package com.Weather.Weather.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Weather.Weather.Model.WeatherResponse;

@Service
public class WeatherService {

    private static final String API_TOKEN = "f502e1e2dbb37435293bf781b957d268";
    private static final String URL = "https://api.openweathermap.org/data/2.5/weather?";

    public WeatherResponse getWeatherFor(String lon, String lat) {
        RestTemplate template = new RestTemplate();

        final String requestUrl = URL + "lat=" + lon + "&lon=" + lat + "&appid=" + API_TOKEN + "&units=metric";

        return template.getForObject(
                requestUrl,
                WeatherResponse.class);
    }

    public WeatherResponse getWeatherFor(String city) {
        RestTemplate template = new RestTemplate();

        final String requestUrl = URL + "q=" + city + "&appid=" + API_TOKEN + "&units=metric";

        return template.getForObject(
                requestUrl,
                WeatherResponse.class);
    }
}

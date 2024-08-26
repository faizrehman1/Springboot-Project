package com.journal.japp.service;

import com.journal.japp.entity.User;
import com.journal.japp.entity.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class WeatherAPIService {


    @Value("${weather.api.key}")
    public String API_KEY;


    @Value("${weather.api.url}")
    public String URL ;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {

        String finalAPI = URL.replace("city", city).replace("API_KEY", API_KEY);
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);

        /*
        // add params and header in calls
        HttpHeaders headers = new HttpHeaders();
        headers.set("","");
        User user = User.builder().userName("").password("").build();
        HttpEntity<String> stringHttpEntity = new HttpEntity<>("",headers);
        HttpEntity<User> userHttpEntity = new HttpEntity<>(user,headers);

        ResponseEntity<WeatherResponse> responseEntity = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse responese = responseEntity.getBody();
        return responese;

         */

        if (weatherResponse != null) {
            return weatherResponse;
        } else {
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body != null) {
                redisService.set("weather_of_" + city, body, 300l);
            }
            return body;
        }



    }
}

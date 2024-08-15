package com.journal.japp.service;

import com.journal.japp.entity.User;
import com.journal.japp.entity.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class WeatherAPIService {

    public static String API_KEY = "54d9cd960b396817e2c611db37a8913a";

    public static String URL = "http://api.weatherstack.com/current?access_key=API_KEY&query=city";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {

        String finalAPI = URL.replace("city", city).replace("API_KEY", API_KEY);

        /*
        // add params and header in calls
        HttpHeaders headers = new HttpHeaders();
        headers.set("","");
        User user = User.builder().userName("").password("").build();
        HttpEntity<String> stringHttpEntity = new HttpEntity<>("",headers);
        HttpEntity<User> userHttpEntity = new HttpEntity<>(user,headers);
         */


        ResponseEntity<WeatherResponse> responseEntity = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse responese = responseEntity.getBody();
        return responese;
    }
}

package com.journal.japp.controller;


import com.journal.japp.entity.User;
import com.journal.japp.entity.WeatherResponse;
import com.journal.japp.request.UserRequest;
import com.journal.japp.response.GetUsersResponse;
import com.journal.japp.service.UserService;
import com.journal.japp.service.WeatherAPIService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Tag(name = "User API",description = "User related API's")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private WeatherAPIService weatherAPIService;

    @GetMapping("/getUsers")
    public Map<String, List<GetUsersResponse>> getAll(){

        Map<String, List<GetUsersResponse>> response = new HashMap<>();
        response.put("users", userService.getAll());

        return response;
    }


    @PostMapping("/createUser")
    public void createUser(@RequestBody UserRequest user){
        userService.saveNewUser(user);
    }


    @PutMapping
    public ResponseEntity<Object> udpateUserByid(@RequestBody UserRequest user){
      Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
      String userName = authentication.getName();
      User user1 =  userService.findbyUserName(userName);

      if(user1!=null){
          user1.setUserName(user.getUserName());
          user1.setPassword(user.getPassword());
       //   userService.saveNewUser(user1);
      }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }


    @PostMapping("greetings")
    public ResponseEntity<?> greeting(@RequestBody String city) {
        JSONObject inputJS = new JSONObject(city);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherAPIService.getWeather(inputJS.optString("city","Karachi"));
        String greeting = "";
        if (weatherResponse != null) {
            greeting = ", Weather feels like " +  weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);
    }

}

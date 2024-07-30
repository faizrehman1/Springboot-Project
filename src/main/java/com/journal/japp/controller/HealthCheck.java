package com.journal.japp.controller;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {


    @GetMapping("/health-check")
    public String checkHealth(){
        return "Ok";
    }


}

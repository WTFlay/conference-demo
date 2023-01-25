package com.pluralsight.conferencedemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @Value("${app.version}")
    private String appVersion;

    @GetMapping()
    public Map<String, Object> getStatus() {
        Map<String, Object> response = new HashMap<>();
        response.put("version", appVersion);
        return response;
    }
}

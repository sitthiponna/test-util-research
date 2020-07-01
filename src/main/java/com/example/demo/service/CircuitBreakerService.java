package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CircuitBreakerService {

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    public String getAlbumList() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
        String url = "http://localhost:7320/circuit/2";

        RestTemplate restTemplate = new RestTemplate();

        return circuitBreaker.run(() -> restTemplate.getForObject(url, String.class),
                throwable -> getDefaultAlbumList());
    }

    public String getDefaultAlbumList() {

        return "First";
    }
}

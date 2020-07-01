package com.example.demo.controller;

import com.example.demo.service.CircuitBreakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResearchController {

    @Autowired
    private CircuitBreakerService circuitBreakerService;

    @GetMapping("/circuit/1")
    public ResponseEntity<String> circuitFirst() {

        return new ResponseEntity<>(circuitBreakerService.getAlbumList(), HttpStatus.OK);
    }

    @GetMapping("/circuit/2")
    public ResponseEntity<String> circuitSecond() {
        try {
            String aa = "as";
            int bb = Integer.parseInt(aa);
            return new ResponseEntity<>("Circuit second " + bb, HttpStatus.OK);
        } catch (Exception ex) {
            ex.getStackTrace();
            return new ResponseEntity<>("Fail circuit second", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

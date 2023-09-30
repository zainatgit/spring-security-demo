package com.zaintech.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
public class DemoController {

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<>("Hello from secured endpoint.", HttpStatus.OK);
    }
}

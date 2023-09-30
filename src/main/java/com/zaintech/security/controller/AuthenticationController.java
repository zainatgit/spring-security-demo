package com.zaintech.security.controller;


import com.zaintech.security.auth.AuthenticationRequest;
import com.zaintech.security.auth.AuthenticationResponse;
import com.zaintech.security.auth.RegisterRequest;
import com.zaintech.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){
        return new ResponseEntity(authenticationService.register(registerRequest), HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authRequest){
        return new ResponseEntity(authenticationService.authenticate(authRequest), HttpStatus.OK);
    }
}

package com.zaintech.security.service;

import com.zaintech.security.auth.AuthenticationRequest;
import com.zaintech.security.auth.AuthenticationResponse;
import com.zaintech.security.auth.RegisterRequest;
import com.zaintech.security.config.JwtService;
import com.zaintech.security.model.Role;
import com.zaintech.security.model.User;
import com.zaintech.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .role(Role.USER)
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}

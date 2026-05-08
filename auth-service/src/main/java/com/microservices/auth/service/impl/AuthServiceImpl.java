package com.microservices.auth.service.impl;

import org.springframework.stereotype.Service;

import com.microservices.auth.dto.AuthResponse;
import com.microservices.auth.dto.LoginRequest;
import com.microservices.auth.entity.User;
import com.microservices.auth.repository.UserRepository;
import com.microservices.auth.security.JwtUtil;
import com.microservices.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid User"));

        String token = jwtUtil.generateToken(user.getUsername());

        return new AuthResponse(token);
    }
}

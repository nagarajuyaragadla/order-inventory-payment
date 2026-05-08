package com.microservices.auth.service;

import com.microservices.auth.dto.AuthResponse;
import com.microservices.auth.dto.LoginRequest;

public interface AuthService {

    AuthResponse login(LoginRequest request);
}
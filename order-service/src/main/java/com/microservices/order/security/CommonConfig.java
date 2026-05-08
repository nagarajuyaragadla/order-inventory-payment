package com.microservices.order.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservies.common.security.JwtService;


@Configuration
public class CommonConfig {

    @Bean
    public JwtService jwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration) {

        return new JwtService(secret, expiration);
    }
}
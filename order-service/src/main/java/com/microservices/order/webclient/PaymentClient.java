package com.microservices.order.webclient;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservies.common.dto.PaymentRequest;
import com.microservies.common.dto.PaymentResponse;


@Service
public class PaymentClient {

    private final WebClient webClient;

    public PaymentClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public PaymentResponse processPayment(PaymentRequest request) {

        return webClient.post()
                .uri("http://localhost:8085/api/payments/process")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PaymentResponse.class)
                .block();
    }
}

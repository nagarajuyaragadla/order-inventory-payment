package com.microservices.payment.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.microservies.common.dto.PaymentRequest;
import com.microservies.common.dto.PaymentResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentService {

    public PaymentResponse processPayment(PaymentRequest request) {

        log.info("Processing payment for order {}", request.getOrderId());

        // fake logic
        PaymentResponse response = new PaymentResponse();
        response.setPaymentId(UUID.randomUUID().toString());
        response.setStatus("SUCCESS");

        return response;
    }
}

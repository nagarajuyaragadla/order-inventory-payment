package com.microservices.payment.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.payment.service.PaymentService;
import com.microservies.common.dto.PaymentRequest;
import com.microservies.common.dto.PaymentResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/process")
    public PaymentResponse process(@RequestBody PaymentRequest request) {

        log.info("Payment request received for order {}", request.getOrderId());

        return paymentService.processPayment(request);
    }
}

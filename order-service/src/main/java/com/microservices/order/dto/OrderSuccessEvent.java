package com.microservices.order.dto;

import lombok.Data;

@Data
public class OrderSuccessEvent {
    private String orderId;
    private String userId;
    private Double amount;
}
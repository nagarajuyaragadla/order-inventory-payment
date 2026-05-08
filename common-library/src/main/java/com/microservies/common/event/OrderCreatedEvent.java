package com.microservies.common.event;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {

    private String orderId;

    private String productCode;

    private Integer quantity;

    private Double price;

    private String customerEmail;

    private String paymentMethod;

    public OrderCreatedEvent(String orderId, String productCode, Integer quantity, Double price) {
        this.orderId = orderId;
        this.productCode = productCode;
        this.quantity = quantity;
        this.price = price;
    }
}

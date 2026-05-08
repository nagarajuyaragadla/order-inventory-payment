package com.microservies.common.event;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentFailedEvent {

    private String orderId;

    private String reason;

    private String status;
}
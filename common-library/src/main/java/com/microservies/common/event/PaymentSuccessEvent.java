
package com.microservies.common.event;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSuccessEvent {

    private String orderId;

    private String paymentId;

    private String status;
}
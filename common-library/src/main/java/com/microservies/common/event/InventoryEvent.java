package com.microservies.common.event;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEvent {

    private String orderId;
    private String productCode;
    private String status;   // SUCCESS / FAILED
    private String message;
}

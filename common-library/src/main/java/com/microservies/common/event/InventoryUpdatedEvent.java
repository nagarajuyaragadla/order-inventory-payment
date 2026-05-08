package com.microservies.common.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryUpdatedEvent {
    private String orderId;
    private String productCode;
    private Integer quantity;
    private boolean available;
    private String status; // RESERVED / FAILED
}
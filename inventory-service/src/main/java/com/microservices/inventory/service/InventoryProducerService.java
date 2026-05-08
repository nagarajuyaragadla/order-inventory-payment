package com.microservices.inventory.service;

public interface InventoryProducerService {
    void publishInventoryUpdated(String orderId, String productCode, String status);

    void publishInventoryFailed(String orderId, String productCode, String reason);
}

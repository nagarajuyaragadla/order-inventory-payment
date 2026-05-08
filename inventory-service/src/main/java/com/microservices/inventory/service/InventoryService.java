package com.microservices.inventory.service;

import com.microservies.common.event.OrderCreatedEvent;

public interface InventoryService {
    void processOrder(OrderCreatedEvent event);
}

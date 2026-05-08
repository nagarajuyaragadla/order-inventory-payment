package com.microservices.inventory.kafka.consumer;

import com.microservices.inventory.service.InventoryService;
import com.microservies.common.event.OrderCreatedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventConsumer {

    private final InventoryService inventoryService;

    @KafkaListener(
            topics = "order-created",
            groupId = "inventory-group"
    )
    public void consume(OrderCreatedEvent event) {

        log.info("Received Order Event:::::::::::: {}", event.getOrderId());

        inventoryService.processOrder(event);
    }
}
package com.microservices.inventory.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.microservices.inventory.service.InventoryProducerService;
import com.microservies.common.event.InventoryEvent;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryProducerServiceImpl implements InventoryProducerService {

    private static final String INVENTORY_TOPIC = "inventory-events";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publishInventoryUpdated(String orderId, String productCode, String status) {

        InventoryEvent event = InventoryEvent.builder()
                .orderId(orderId)
                .productCode(productCode)
                .status(status) // SUCCESS
                .message("Inventory updated successfully")
                .build();

        log.info("Publishing INVENTORY SUCCESS event: {}", event);

        kafkaTemplate.send(INVENTORY_TOPIC, orderId, event);
    }

    @Override
    public void publishInventoryFailed(String orderId, String productCode, String reason) {

        InventoryEvent event = InventoryEvent.builder()
                .orderId(orderId)
                .productCode(productCode)
                .status("FAILED")
                .message(reason)
                .build();

        log.error("Publishing INVENTORY FAILED event: {}", event);

        kafkaTemplate.send(INVENTORY_TOPIC, orderId, event);
    }
}


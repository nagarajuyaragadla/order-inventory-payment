package com.microservices.inventory.service.impl;

import com.microservices.inventory.entity.Inventory;
import com.microservices.inventory.repository.InventoryRepository;
import com.microservices.inventory.service.InventoryProducerService;
import com.microservices.inventory.service.InventoryService;
import com.microservies.common.event.InventoryUpdatedEvent;
import com.microservies.common.event.OrderCreatedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryProducerService inventoryProducer;

    @Override
    public void processOrder(OrderCreatedEvent event) {

        log.info("Processing inventory for order: {}", event.getOrderId());

        inventoryRepository.findByProductCode(event.getProductCode())
                .ifPresentOrElse(inventory -> {

                    if (inventory.getAvailableQuantity() < event.getQuantity()) {
                        log.error("Insufficient stock");
                        inventoryProducer.publishInventoryFailed(
                                event.getOrderId(),
                                event.getProductCode(),
                                "INSUFFICIENT_STOCK"
                        );
                        return;
                    }

                    inventory.setAvailableQuantity(
                            inventory.getAvailableQuantity() - event.getQuantity()
                    );

                    inventoryRepository.save(inventory);

                    inventoryProducer.publishInventoryUpdated(
                            event.getOrderId(),
                            event.getProductCode(),
                            "SUCCESS"
                    );

                }, () -> {
                    log.error("Product not found: {}", event.getProductCode());

                    inventoryProducer.publishInventoryFailed(
                            event.getOrderId(),
                            event.getProductCode(),
                            "PRODUCT_NOT_FOUND"
                    );
                });
    }
}
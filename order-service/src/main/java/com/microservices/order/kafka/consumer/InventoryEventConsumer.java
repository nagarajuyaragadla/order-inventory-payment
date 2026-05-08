package com.microservices.order.kafka.consumer;

import com.microservices.order.webclient.PaymentClient;
import com.microservies.common.dto.PaymentRequest;
import com.microservies.common.dto.PaymentResponse;
import com.microservies.common.event.InventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryEventConsumer {

    private final PaymentClient paymentClient;

    @KafkaListener(topics = "inventory-events", groupId = "order-group")
    public void consumeInventoryEvent(InventoryEvent event) {

        log.info("Received Inventory Event::::::::::::: {}", event);
        event.setStatus("SUCCESS");
        if ("SUCCESS".equalsIgnoreCase(event.getStatus())) {

            log.info("Inventory confirmed for orderId: {}", event.getOrderId());

            // 👉 update order status = INVENTORY_CONFIRMED
            // orderService.updateStatus(event.getOrderId(), "INVENTORY_CONFIRMED");

            PaymentRequest request = new PaymentRequest(
                    event.getOrderId(),
                    event.getProductCode(),
                    10.5);

            PaymentResponse response = paymentClient.processPayment(request);
            log.info("Payemene response for orderId {},{} ", event.getOrderId(),response);

        } else {

            log.error("Inventory failed for orderId::::::::::: {}, reason: {}",
                    event.getOrderId(),
                    event.getMessage());

            // 👉 rollback or mark order as FAILED
            // orderService.updateStatus(event.getOrderId(), "FAILED");
        }
    }
}

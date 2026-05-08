package com.microservices.order.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.microservies.common.event.OrderCreatedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishOrderCreatedEvent(OrderCreatedEvent event) {

        kafkaTemplate.send(
                "order-created",
                event.getOrderId(),
                event
        );

        log.info("Published order-created event {}", event.getOrderId());
    }
}
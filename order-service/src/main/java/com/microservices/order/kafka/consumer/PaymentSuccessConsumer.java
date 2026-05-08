package com.microservices.order.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.microservices.order.entity.Order;
import com.microservices.order.entity.OrderStatus;
import com.microservices.order.repository.OrderRepository;
import com.microservies.common.event.PaymentSuccessEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentSuccessConsumer {

    private final OrderRepository orderRepository;

    @KafkaListener(
            topics = "payment-success",
            groupId = "order-group"
    )
    public void consume(PaymentSuccessEvent event) {

        Order order = orderRepository.findByOrderId(event.getOrderId())
                .orElseThrow();

        order.setStatus(OrderStatus.COMPLETED);

        orderRepository.save(order);

        log.info("Order Completed {}", event.getOrderId());
    }
}

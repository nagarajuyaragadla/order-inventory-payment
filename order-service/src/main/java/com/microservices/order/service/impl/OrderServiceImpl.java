package com.microservices.order.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.microservices.order.dto.OrderRequest;
import com.microservices.order.dto.OrderResponse;
import com.microservices.order.entity.Order;
import com.microservices.order.entity.OrderStatus;
import com.microservices.order.repository.OrderRepository;
import com.microservices.order.service.OrderService;
import com.microservies.common.event.OrderCreatedEvent;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest request) {

        String orderId = UUID.randomUUID().toString();

        Order order = Order.builder()
                .orderId(orderId)
                .productCode(request.getProductCode())
                .quantity(request.getQuantity())
                .amount(request.getPrice())
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .customerName(request.getCustomerName())
                .build();

        orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(
                orderId,
                request.getProductCode(),
                request.getQuantity(),
                request.getPrice()
        );

        kafkaTemplate.send("order-created", orderId, event);

        log.info("Order Created Event Published {}", orderId);

        return OrderResponse.builder()
        .orderId(order.getOrderId())
        .status(order.getStatus().name())
        .message("Order Created Successfully")
        .build();
    }
}

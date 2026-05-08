package com.microservices.order.service;

import com.microservices.order.dto.OrderRequest;
import com.microservices.order.dto.OrderResponse;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);
}

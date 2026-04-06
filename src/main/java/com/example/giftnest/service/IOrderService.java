package com.example.giftnest.service;


import com.example.giftnest.dto.OrderRequestDto;
import com.example.giftnest.dto.OrderResponseDto;

import java.util.List;

public interface IOrderService {
    void createOrder(OrderRequestDto orderRequest);
    List<OrderResponseDto> getCustomerOrders();

    List<OrderResponseDto> getAllPendingOrders();

    void updateOrderStatus(Long orderId, String orderStatus);
}

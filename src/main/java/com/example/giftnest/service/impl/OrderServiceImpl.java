package com.example.giftnest.service.impl;


import com.example.giftnest.constants.ApplicationConstants;
import com.example.giftnest.dto.OrderItemResponseDto;
import com.example.giftnest.dto.OrderRequestDto;
import com.example.giftnest.dto.OrderResponseDto;
import com.example.giftnest.entity.Customer;
import com.example.giftnest.entity.Order;
import com.example.giftnest.entity.OrderItem;
import com.example.giftnest.entity.Product;
import com.example.giftnest.exception.ResourceNotFoundException;
import com.example.giftnest.repository.OrderRepository;
import com.example.giftnest.repository.ProductRepository;
import com.example.giftnest.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final ProfileServiceImpl profileService;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    @Override
    public void createOrder(OrderRequestDto orderRequest) {
        Customer customer = profileService.getAuthenticatedCustomer();
        Order order = new Order();
        order.setCustomer(customer);
        BeanUtils.copyProperties(orderRequest,order);
        order.setOrderStatus(ApplicationConstants.ORDER_STATUS_CREATED);

        List<OrderItem> orderItems = orderRequest.items().stream().map(item->{
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            Product product = productRepository.findById(item.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product", "ProductID",
                            item.productId().toString()));
            orderItem.setProduct(product);
            orderItem.setPrice(item.price());
            orderItem.setQuantity(item.quantity());
            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        orderRepository.save(order);
    }

    @Override
    public List<OrderResponseDto> getCustomerOrders() {
        Customer customer = profileService.getAuthenticatedCustomer();
        //Method-1 : Spring JPA
        //List<Order> orders = orderRepository.findByCustomerOrderByCreatedAtDesc(customer);

        //Method-2 : Custom query using JPQL
        //List<Order> orders = orderRepository.findOrdersByCustomer(customer);

        //Method-3 : Custom query using Native
        List<Order> orders = orderRepository.findOrdersByCustomerWithNativeQuery(customer.getCustomerId());
        return orders.stream().map(this::mapToOrderResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDto> getAllPendingOrders() {
        //Method-1 : Spring JPA
        //List<Order> orders = orderRepository.findByOrderStatus(ApplicationConstants.ORDER_STATUS_CREATED);

        //Method-2 : Custom query using JPQL
        //List<Order> orders = orderRepository.findOrdersByStatus(ApplicationConstants.ORDER_STATUS_CREATED);

        //Method-3 : Custom query using Native
        List<Order> orders = orderRepository.findOrdersByStatusWithNativeQuery(ApplicationConstants.ORDER_STATUS_CREATED);

        return orders.stream().map(this::mapToOrderResponseDTO).collect(Collectors.toList());
    }

    @Override
    public void updateOrderStatus(Long orderId, String orderStatus) {
        // Method-1 : using Spring JPA
        /*
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Order", "OrderID", orderId.toString())
        );
        order.setOrderStatus(orderStatus);
        return orderRepository.save(order);
        */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        orderRepository.updateOrderStatus(orderId,orderStatus,email);
    }


    private OrderResponseDto mapToOrderResponseDTO(Order order){
        List<OrderItemResponseDto> itemsDto = order.getOrderItems().stream().map(this::mapToOrderItemResponseDTO).collect(Collectors.toList());
        OrderResponseDto orderResponseDto = new OrderResponseDto(order.getOrderId(),order.getOrderStatus(),order.getTotalPrice(),order.getCreatedAt().toString(),itemsDto);
        return orderResponseDto;
    }

    private OrderItemResponseDto mapToOrderItemResponseDTO(OrderItem orderItem){
        OrderItemResponseDto itemDto = new OrderItemResponseDto(orderItem.getProduct().getName(),orderItem.getQuantity(),orderItem.getPrice(),orderItem.getProduct().getImageUrl());
        return itemDto;
    }
}

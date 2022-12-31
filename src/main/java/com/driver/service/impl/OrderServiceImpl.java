package com.driver.service.impl;

import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Override
    public OrderDto createOrder(OrderDto order) {
        OrderEntity orderEntity=OrderEntity.builder().
                id(order.getId()).
                orderId(order.getOrderId()).
                userId(order.getUserId()).
                cost(order.getCost()).
                items(order.getItems()).
                status(order.isStatus()).
                build();
        orderRepository.save(orderEntity);
        return order;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
        OrderDto orderDto=OrderDto.builder().
                id(orderEntity.getId()).
                orderId(orderEntity.getOrderId()).
                userId(orderEntity.getUserId()).
                cost(orderEntity.getCost()).
                items(orderEntity.getItems()).
                status(orderEntity.isStatus()).
                build();
        return orderDto;
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderEntity orderEntity=OrderEntity.builder().
                orderId(order.getOrderId()).
                id(order.getId()).
                userId(order.getUserId()).
                status(order.isStatus()).
                cost(order.getCost()).
                items(order.getItems()).
                build();
        orderRepository.save(orderEntity);
        return order;
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
        orderRepository.delete(orderEntity);
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderDto> orderDtos=new ArrayList<>();
        List<OrderEntity> orderEntities=(List<OrderEntity>)orderRepository.findAll();
        for(OrderEntity orderEntity : orderEntities){
            orderDtos.add(OrderDto.builder().
                    id(orderEntity.getId()).
                    orderId(orderEntity.getOrderId()).
                    userId(orderEntity.getUserId()).
                    cost(orderEntity.getCost()).
                    items(orderEntity.getItems()).
                    status(orderEntity.isStatus()).
                    build());
        }
        return orderDtos;
    }
}
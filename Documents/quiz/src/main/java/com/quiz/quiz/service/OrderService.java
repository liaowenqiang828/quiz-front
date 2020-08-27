package com.quiz.quiz.service;

import com.quiz.quiz.Dto.OrderDto;
import com.quiz.quiz.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ResponseEntity getOrderList() {
        List<OrderDto> list = orderRepository.findAll();
        return ResponseEntity.ok(list);
    }
}

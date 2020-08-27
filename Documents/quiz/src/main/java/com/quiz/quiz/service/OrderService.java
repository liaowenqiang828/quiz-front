package com.quiz.quiz.service;

import com.quiz.quiz.Dto.OrderDto;
import com.quiz.quiz.Dto.ProductDto;
import com.quiz.quiz.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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

    public ResponseEntity deleteProductFromOrderTableById(int id) {
        orderRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

package com.quiz.quiz.api;

import com.quiz.quiz.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @CrossOrigin
    @GetMapping("/orders")
    public ResponseEntity getOrderList() {
        return orderService.getOrderList();
    }
}

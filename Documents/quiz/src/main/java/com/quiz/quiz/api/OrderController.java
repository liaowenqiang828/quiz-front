package com.quiz.quiz.api;

import com.quiz.quiz.Dto.ProductDto;
import com.quiz.quiz.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin
    @DeleteMapping("/order/{id}")
    public ResponseEntity deleteProductFromOrderTableById(@PathVariable int id) {
        return orderService.deleteProductFromOrderTableById(id);
    }
}

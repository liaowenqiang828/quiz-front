package com.quiz.quiz.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.quiz.Dto.OrderDto;
import com.quiz.quiz.Dto.ProductDto;
import com.quiz.quiz.repository.OrderRepository;
import com.quiz.quiz.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ProductService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public ResponseEntity getAllProducts() {
        List<ProductDto> list = productRepository.findAll();
        return ResponseEntity.ok(list);
    }

    public ResponseEntity addProductToMall(String string) throws JsonProcessingException {
        ProductDto productDto = objectMapper.readValue(string, ProductDto.class);

        OrderDto orderDto = OrderDto.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .unit(productDto.getUnit())
                .product(productDto)
                .count(1)
                .build();
        List<OrderDto> list = orderRepository.findAll();
        AtomicBoolean isAdd = new AtomicBoolean(false);

        list.stream().forEach(item -> {
            if (item.getName().equals(item.getName())) {
                item.setCount(item.getCount() + 1);
                isAdd.set(true);
            };
        });
        if (!isAdd.get()) {
            list.add(orderDto);
        }
        orderRepository.saveAll(list);
        return ResponseEntity.ok().build();
    }
}

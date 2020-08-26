package com.quiz.quiz.service;

import com.quiz.quiz.Dto.ProductDto;
import com.quiz.quiz.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity getAllProducts() {
        List<ProductDto> list = productRepository.findAll();
        return ResponseEntity.ok(list);
    }
}

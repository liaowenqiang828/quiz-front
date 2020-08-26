package com.quiz.quiz.api;

import com.quiz.quiz.repository.ProductRepository;
import com.quiz.quiz.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @CrossOrigin
    @GetMapping("/products")
    public ResponseEntity getAllProducts() {
        return productService.getAllProducts();
    }
}

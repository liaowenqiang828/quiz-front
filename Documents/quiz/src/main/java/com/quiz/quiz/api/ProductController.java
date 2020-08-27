package com.quiz.quiz.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.quiz.quiz.repository.ProductRepository;
import com.quiz.quiz.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin
    @PostMapping("/product")
    public ResponseEntity addProductToMall(@RequestBody String string) throws JsonProcessingException {
        return productService.addProductToMall(string);
    }

}

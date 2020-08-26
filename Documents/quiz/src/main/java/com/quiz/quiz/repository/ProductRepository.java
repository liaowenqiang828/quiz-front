package com.quiz.quiz.repository;

import com.quiz.quiz.Dto.ProductDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductDto, Integer> {
    List<ProductDto> findAll();
}

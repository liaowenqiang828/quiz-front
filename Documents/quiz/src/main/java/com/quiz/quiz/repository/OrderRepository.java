package com.quiz.quiz.repository;

import com.quiz.quiz.Dto.OrderDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderDto, Integer> {
    List<OrderDto> findAll();
}

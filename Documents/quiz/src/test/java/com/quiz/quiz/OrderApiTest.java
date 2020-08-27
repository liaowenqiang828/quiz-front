package com.quiz.quiz;

import com.quiz.quiz.Dto.OrderDto;
import com.quiz.quiz.Dto.ProductDto;
import com.quiz.quiz.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderApiTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void getOrderList() throws Exception {
        ProductDto productDto = ProductDto.builder()
                .name("茅台")
                .price(1299.00)
                .imageUrl("https://icon.qiantucdn.com/20200820/83d12074ff9be098864ec790fb7e75d22")
                .unit("瓶")
                .build();
        OrderDto orderDto = OrderDto.builder()
                .count(1)
                .product(productDto)
                .unit(productDto.getUnit())
                .price(productDto.getPrice())
                .name(productDto.getName())
                .build();
        orderRepository.save(orderDto);

        mockMvc.perform(get("/orders").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        List<OrderDto> orderDtoList = orderRepository.findAll();
        assertEquals(orderDtoList.size(), 1);
        assertEquals(orderDtoList.get(0).getName(), "茅台");
        assertEquals(orderDtoList.get(0).getPrice(), 1299.00);
        assertEquals(orderDtoList.get(0).getUnit(), "瓶");
    }
}

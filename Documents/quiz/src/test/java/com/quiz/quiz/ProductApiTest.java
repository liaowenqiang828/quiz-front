package com.quiz.quiz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.quiz.Dto.OrderDto;
import com.quiz.quiz.Dto.ProductDto;
import com.quiz.quiz.repository.OrderRepository;
import com.quiz.quiz.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductApiTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
        ProductDto productDto1 = ProductDto.builder()
                .name("可乐")
                .price(2.5)
                .imageUrl("https://icon.qiantucdn.com/20200814/70443c34ad405dd21cedec2e259da1052")
                .unit("瓶")
                .build();
        ProductDto productDto2 = ProductDto.builder()
                .name("王老吉")
                .price(3.5)
                .imageUrl("https://icon.qiantucdn.com/20200819/c7fa501aa13c3e02705876ec606af69b2")
                .unit("瓶")
                .build();
        ProductDto productDto3 = ProductDto.builder()
                .name("苹果")
                .price(4)
                .imageUrl("https://icon.qiantucdn.com/20200819/3dfd3751c66af060d2cb6998969adf462")
                .unit("斤")
                .build();
        ProductDto productDto4 = ProductDto.builder()
                .name("橘子")
                .price(3.5)
                .imageUrl("https://icon.qiantucdn.com/20200819/521c521930d1fe796509c960b06d5e942")
                .unit("斤")
                .build();
        ProductDto productDto5 = ProductDto.builder()
                .name("啤酒")
                .price(15)
                .imageUrl("https://icon.qiantucdn.com/20200820/83d12074ff9be098864ec790fb7e75d22")
                .unit("瓶")
                .build();
        productRepository.save(productDto1);
        productRepository.save(productDto2);
        productRepository.save(productDto3);
        productRepository.save(productDto4);
        productRepository.save(productDto5);
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    void getProducts() throws Exception {
        mockMvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[4].name", is("啤酒")))
                .andExpect(jsonPath("$[4].price", is(15.0)))
                .andExpect(jsonPath("$[4].unit", is("瓶")))
                .andExpect(jsonPath("$[4].imageUrl", is("https://icon.qiantucdn.com/20200820/83d12074ff9be098864ec790fb7e75d22")));
    }

    @Test
    void addProductToMall() throws Exception {
        ProductDto productDto = ProductDto.builder()
                .name("茅台")
                .price(1299.00)
                .imageUrl("https://icon.qiantucdn.com/20200820/83d12074ff9be098864ec790fb7e75d22")
                .unit("瓶")
                .build();
        String jsonString = objectMapper.writeValueAsString(productDto);
        mockMvc.perform(post("/product").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        List<OrderDto> orderDtoList = orderRepository.findAll();
        assertEquals(orderDtoList.size(), 1);
        assertEquals(orderDtoList.get(0).getName(), "茅台");
        assertEquals(orderDtoList.get(0).getPrice(), 1299.00);
        assertEquals(orderDtoList.get(0).getCount(), 1);
        assertEquals(orderDtoList.get(0).getUnit(), "瓶");
    }

}

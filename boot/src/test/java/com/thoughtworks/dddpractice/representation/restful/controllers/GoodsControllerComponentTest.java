package com.thoughtworks.dddpractice.representation.restful.controllers;

import com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods.JpaGoodsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GoodsControllerComponentTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  JpaGoodsRepository jpaGoodsRepository;

  @AfterEach
  public void teardown() {
    jpaGoodsRepository.deleteAll();
  }

  @Test
  void should_create_goods_successful() throws Exception {
    //given
    String requestBody = "{\n" +
      "\"code\": \"001\",\n" +
      "\"name\": \"apple\",\n" +
      "\"price\": 3.5\n" +
      "}";

    //when
    ResultActions result = mockMvc
      .perform(post("/goods")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody));

    //then
    result.andExpect(status().isCreated())
      .andExpect(jsonPath("$.code", is("001")))
      .andExpect(jsonPath("$.name", is("apple")))
      .andExpect(jsonPath("$.price", is(3.5)))
      .andExpect(jsonPath("$.aggregateId", is(notNullValue())));
  }
}

package com.thoughtworks.dddpractice.representation.restful.controllers;

import com.thoughtworks.dddpractice.application.ObjectMapper;
import com.thoughtworks.dddpractice.application.command.GoodsCreateCommand;
import com.thoughtworks.dddpractice.application.service.GoodsReadService;
import com.thoughtworks.dddpractice.application.service.GoodsWriteService;
import com.thoughtworks.dddpractice.domain.goods.Goods;
import com.thoughtworks.dddpractice.domain.goods.GoodsFactory;
import com.thoughtworks.dddpractice.domain.goods.GoodsRepository;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GoodsController.class)
@ContextConfiguration(classes = GoodsController.class)
class GoodsControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  GoodsWriteService goodsWriteService;

  @MockBean
  GoodsReadService goodsReadService;

  GoodsFactory goodsFactory;

  @BeforeEach
  void setUp() {
    goodsFactory = new GoodsFactory(
      mock(DomainEventPublisher.class),
      mock(GoodsRepository.class));
  }

  @Test
  void should_create_goods_successful() throws Exception {
    //given
    String requestBody = "{\n" +
      "\"code\": \"001\",\n" +
      "\"name\": \"apple\",\n" +
      "\"price\": 3.5\n" +
      "}";
    GoodsCreateCommand command = GoodsCreateCommand.builder().code("001").name("apple").price(3.5).build();
    Goods goods = goodsFactory.create(ObjectMapper.MAPPER.commandToVO(command));
    when(goodsWriteService.create(command))
      .thenReturn(goods);

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

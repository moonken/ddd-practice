package com.thoughtworks.dddpractice.representation.restful.controllers;

import com.thoughtworks.dddpractice.application.service.GoodsReadService;
import com.thoughtworks.dddpractice.application.service.GoodsWriteService;
import com.thoughtworks.dddpractice.domain.goods.GoodsFactory;
import com.thoughtworks.dddpractice.domain.goods.GoodsRepository;
import com.thoughtworks.dddpractice.domain.goods.GoodsService;
import com.thoughtworks.dddpractice.domain.goods.dto.GoodsDTO;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods.GoodsPO;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods.JpaGoodsRepository;
import com.thoughtworks.dddpractice.representation.restful.request.GoodsCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GoodsControllerUnitTest {

  @Mock
  GoodsRepository goodsRepository;

  @Mock
  DomainEventPublisher domainEventPublisher;

  @Mock
  JpaGoodsRepository jpaGoodsRepository;

  GoodsFactory goodsFactory;

  GoodsService goodsService;

  GoodsWriteService goodsWriteService;
  GoodsReadService goodsReadService;

  GoodsController goodsController;

  @BeforeEach
  void setUp() {
    goodsFactory = new GoodsFactory(domainEventPublisher, goodsRepository);
    goodsService = new GoodsService(goodsFactory, goodsRepository, domainEventPublisher);

    goodsWriteService = new GoodsWriteService(goodsService, goodsRepository);
    goodsReadService = new GoodsReadService(jpaGoodsRepository);

    goodsController = new GoodsController(goodsWriteService, goodsReadService);
  }

  @Test
  void should_create_goods_successful() {
    //given
    GoodsCreateRequest goodsCreateRequest = new GoodsCreateRequest("001", "apple", 3.5);

    //when
    GoodsDTO goodsDTO = goodsController.create(goodsCreateRequest);

    //then
    assertThat(goodsDTO.getCode(), is("001"));
    assertThat(goodsDTO.getName(), is("apple"));
    assertThat(goodsDTO.getPrice(), is(3.5));
    assertThat(goodsDTO.getAggregateId(), is(notNullValue()));
  }

  @Test
  void should_get_all_goods_successful() {
    //given
    when(jpaGoodsRepository.findAll()).thenReturn(asList(
      new GoodsPO(UUID.randomUUID().toString(), "001", "apple", 3.5),
      new GoodsPO(UUID.randomUUID().toString(), "002", "banana", 2.5)
    ));

    //when
    List<GoodsDTO> goodsResponses = goodsController.getAll();

    //then
    assertThat(goodsResponses.size(), is(2));
    assertThat(goodsResponses.stream().map(GoodsDTO::getCode).collect(toList()), containsInAnyOrder("001", "002"));
  }
}

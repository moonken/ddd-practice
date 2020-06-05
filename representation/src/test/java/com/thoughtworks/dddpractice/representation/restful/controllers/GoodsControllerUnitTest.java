package com.thoughtworks.dddpractice.representation.restful.controllers;

import com.thoughtworks.dddpractice.application.command.GoodsCreateCommand;
import com.thoughtworks.dddpractice.application.service.GoodsReadService;
import com.thoughtworks.dddpractice.application.service.GoodsWriteService;
import com.thoughtworks.dddpractice.domain.goods.GoodsFactory;
import com.thoughtworks.dddpractice.domain.goods.GoodsRepository;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods.GoodsPO;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods.JpaGoodsRepository;
import com.thoughtworks.dddpractice.representation.dto.GoodsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

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
  AutowireCapableBeanFactory autowireCapableBeanFactory;

  @Mock
  JpaGoodsRepository jpaGoodsRepository;

  GoodsFactory goodsFactory;

  GoodsWriteService goodsWriteService;
  GoodsReadService goodsReadService;

  GoodsController goodsController;

  @BeforeEach
  void setUp() {
    goodsFactory = new GoodsFactory(domainEventPublisher, goodsRepository,
      autowireCapableBeanFactory);

    goodsWriteService = new GoodsWriteService(goodsFactory, goodsRepository);
    goodsReadService = new GoodsReadService(jpaGoodsRepository);

    goodsController = new GoodsController(goodsWriteService, goodsReadService);
  }

  @Test
  void should_create_goods_successful() {
    //given
    GoodsCreateCommand goodsCreateCommand = new GoodsCreateCommand("001", "apple", 3.5);

    //when
    GoodsDTO goodsVO = goodsController.create(goodsCreateCommand);

    //then
    assertThat(goodsVO.getCode(), is("001"));
    assertThat(goodsVO.getName(), is("apple"));
    assertThat(goodsVO.getPrice(), is(3.5));
    assertThat(goodsVO.getAggregateId(), is(notNullValue()));
  }

  @Test
  void should_get_all_goods_successful() {
    //given
    when(jpaGoodsRepository.findAll()).thenReturn(asList(
      new GoodsPO(UUID.randomUUID().toString(), "001", "apple", 3.5),
      new GoodsPO(UUID.randomUUID().toString(), "002", "banana", 2.5)
    ));

    //when
    List<GoodsDTO> goodsVOs = goodsController.getAll();

    //then
    assertThat(goodsVOs.size(), is(2));
    assertThat(goodsVOs.stream().map(GoodsDTO::getCode).collect(toList()), containsInAnyOrder("001", "002"));
  }
}

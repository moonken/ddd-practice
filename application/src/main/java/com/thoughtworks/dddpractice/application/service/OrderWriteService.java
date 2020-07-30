package com.thoughtworks.dddpractice.application.service;

import com.thoughtworks.dddpractice.application.command.OrderCreateCommand;
import com.thoughtworks.dddpractice.domain.order.Order;
import com.thoughtworks.dddpractice.domain.order.OrderItem;
import com.thoughtworks.dddpractice.domain.order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class OrderWriteService {
  private final OrderService orderService;

  public Order create(OrderCreateCommand command) {
    return orderService.create(command.getCustomerId(),
      command.getItems().stream().map(
        item -> OrderItem.builder()
          .goodsId(item.getGoodsId())
          .quanqtity(item.getQuantity())
          .build())
        .collect(toList()));
  }
}

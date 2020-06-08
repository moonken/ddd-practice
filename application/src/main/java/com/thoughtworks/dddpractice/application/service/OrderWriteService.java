package com.thoughtworks.dddpractice.application.service;

import com.thoughtworks.dddpractice.application.ObjectMapper;
import com.thoughtworks.dddpractice.application.command.OrderCreateCommand;
import com.thoughtworks.dddpractice.domain.order.Order;
import com.thoughtworks.dddpractice.domain.order.OrderRepository;
import com.thoughtworks.dddpractice.domain.order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class OrderWriteService {
  private final OrderService orderService;

  public Order create(OrderCreateCommand command) {
    return orderService.create(ObjectMapper.MAPPER.commandToDTO(command));
  }
}

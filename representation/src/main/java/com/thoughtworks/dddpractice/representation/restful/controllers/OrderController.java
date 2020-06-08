package com.thoughtworks.dddpractice.representation.restful.controllers;

import com.thoughtworks.dddpractice.application.service.OrderReadService;
import com.thoughtworks.dddpractice.application.service.OrderWriteService;
import com.thoughtworks.dddpractice.domain.order.Order;
import com.thoughtworks.dddpractice.domain.order.dto.OrderDTO;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.order.OrderPO;
import com.thoughtworks.dddpractice.representation.dto.OrderMapper;
import com.thoughtworks.dddpractice.representation.restful.request.OrderCreateRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("orders")
@AllArgsConstructor
public class OrderController {
  private final OrderWriteService orderWriterService;
  private final OrderReadService orderReadService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OrderDTO create(@RequestBody @Valid OrderCreateRequest request) {
    Order order = orderWriterService.create(OrderMapper.MAPPER.toCommand(request));
    return OrderMapper.MAPPER.toDTO(order);
  }

  @GetMapping("{id}")
  public OrderDTO get(@PathVariable String id) {
    OrderPO orderPO = orderReadService.getPO(id);
    return OrderMapper.MAPPER.toDTO(orderPO);
  }

  @GetMapping
  public List<OrderDTO> getAll() {
    List<OrderPO> orders = orderReadService.getAllPO();
    return OrderMapper.MAPPER.poToDTOs(orders);
  }

}

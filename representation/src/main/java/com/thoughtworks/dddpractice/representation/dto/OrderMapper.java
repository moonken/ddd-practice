package com.thoughtworks.dddpractice.representation.dto;

import com.thoughtworks.dddpractice.application.command.OrderCreateCommand;
import com.thoughtworks.dddpractice.domain.order.Order;
import com.thoughtworks.dddpractice.domain.order.OrderItem;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.order.OrderItemPO;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.order.OrderPO;
import com.thoughtworks.dddpractice.representation.restful.request.OrderCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedSourcePolicy = IGNORE, unmappedTargetPolicy = IGNORE)
public interface OrderMapper {

  OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

  OrderDTO toDTO(OrderPO order);

  OrderDTO toDTO(Order order);

  OrderItemDTO toDTO(OrderItemPO orderItemPO);

  OrderItemDTO toDTO(OrderItem orderItem);

  List<OrderDTO> poToDTOs(List<OrderPO> orders);

  OrderCreateCommand toCommand(OrderCreateRequest request);

  OrderCreateCommand.OrderItem toCommand(OrderCreateRequest.OrderItem request);
}

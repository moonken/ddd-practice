package com.thoughtworks.dddpractice.domain.order.dto;

import com.thoughtworks.dddpractice.framework.annotations.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ValueObject
public class OrderDTO {
  private String aggregateId;
  private String customerId;
  private List<OrderItemDTO> items;
  private double freight;
  private double discount;
}

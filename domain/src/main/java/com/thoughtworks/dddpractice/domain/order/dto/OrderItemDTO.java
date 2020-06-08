package com.thoughtworks.dddpractice.domain.order.dto;

import com.thoughtworks.dddpractice.framework.annotations.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@ValueObject
public class OrderItemDTO {
  private String entityId;
  private String goodsId;
  private Double quality;
  private double discount;
}

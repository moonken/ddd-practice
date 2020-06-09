package com.thoughtworks.dddpractice.domain.order.dto;

import com.thoughtworks.dddpractice.domain.order.GoodsSnapshot;
import com.thoughtworks.dddpractice.framework.annotations.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderItemDTO {
  private String entityId;
  private String goodsId;
  private Double quality;
  private double discount;
  private GoodsSnapshot goods;

  public OrderItemDTO(String goodsId, double quality) {
    this.goodsId = goodsId;
    this.quality = quality;
  }
}

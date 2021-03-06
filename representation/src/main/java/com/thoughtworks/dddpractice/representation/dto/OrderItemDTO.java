package com.thoughtworks.dddpractice.representation.dto;

import com.thoughtworks.dddpractice.domain.order.GoodsSnapshot;
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
public class OrderItemDTO {
  private String entityId;
  private String goodsId;
  private Double quantity;
  private double discount;
  private GoodsSnapshot goods;

  public OrderItemDTO(String goodsId, double quantity) {
    this.goodsId = goodsId;
    this.quantity = quantity;
  }
}

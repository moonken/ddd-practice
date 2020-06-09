package com.thoughtworks.dddpractice.domain.order;

import com.thoughtworks.dddpractice.domain.order.dto.OrderItemDTO;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainEntity;
import com.thoughtworks.dddpractice.framework.support.domain.BaseEntity;
import lombok.Getter;

import java.math.BigDecimal;

@DomainEntity
@Getter
public class OrderItem extends BaseEntity {
  public static final double NO_DISCOUNT = 1;

  private String entityId;
  private String goodsId;
  private GoodsSnapshot goods;
  private Double quality;
  private double discount;

  OrderItem(String generatedId, OrderItemDTO vo) {
    this.entityId = generatedId;
    this.goodsId = vo.getGoodsId();
    this.quality = vo.getQuality();
    this.goods = vo.getGoods();
    this.discount = NO_DISCOUNT;
  }

  OrderItem(OrderItemDTO vo) {
    this.entityId = vo.getEntityId();
    this.goodsId = vo.getGoodsId();
    this.quality = vo.getQuality();
    this.goods = vo.getGoods();
    this.discount = vo.getDiscount();
  }

  public BigDecimal getSubTotal() {
    return this.goods.getPrice().multiply(BigDecimal.valueOf(quality)).multiply(BigDecimal.valueOf(discount));
  }
}

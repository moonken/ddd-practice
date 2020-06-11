package com.thoughtworks.dddpractice.domain.order;

import com.thoughtworks.dddpractice.framework.annotations.domain.DomainEntity;
import com.thoughtworks.dddpractice.framework.support.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@DomainEntity
@Getter
@Builder
public class OrderItem extends BaseEntity {
  public static final double NO_DISCOUNT = 1;

  private String entityId;
  private String goodsId;
  @Setter
  private GoodsSnapshot goods;
  private Double quality;
  private double discount;

  OrderItem(String entityId, String goodsId, GoodsSnapshot goods, Double quality, double discount) {
    if (entityId == null) {
      this.entityId = UUID.randomUUID().toString();
      this.discount = NO_DISCOUNT;
    } else {
      this.entityId = entityId;
      this.discount = discount;
    }
    this.goodsId = goodsId;
    this.goods = goods;
    this.quality = quality;
  }


  public BigDecimal getSubTotal() {
    return this.goods.getPrice().multiply(BigDecimal.valueOf(quality)).multiply(BigDecimal.valueOf(discount));
  }
}

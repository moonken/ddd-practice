package com.thoughtworks.dddpractice.infrastructure.repository.jpa.order;

import com.thoughtworks.dddpractice.domain.order.GoodsSnapshot;
import lombok.Data;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Data
public class GoodsSnapshotPO {
  private String code;
  private String name;
  private BigDecimal price;

  public GoodsSnapshot toDomain() {
    return new GoodsSnapshot(code, name, price);
  }
}

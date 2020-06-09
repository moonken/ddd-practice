package com.thoughtworks.dddpractice.infrastructure.repository.jpa.order;

import lombok.Data;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Data
public class GoodsSnapshotPO {
  private String code;
  private String name;
  private BigDecimal price;
}

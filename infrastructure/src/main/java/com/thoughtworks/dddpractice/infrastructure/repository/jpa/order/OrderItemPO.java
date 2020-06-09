package com.thoughtworks.dddpractice.infrastructure.repository.jpa.order;

import com.thoughtworks.dddpractice.domain.order.GoodsSnapshot;
import com.thoughtworks.dddpractice.framework.support.domain.BaseEntityPO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemPO extends BaseEntityPO {

  @Id
  @Column(name = "id")
  protected String entityId;

  @Column(name = "order_id")
  private String orderId;

  @Embedded
  private GoodsSnapshotPO goods;

  private String goodsId;
  private Double quality;
  private double discount;
}

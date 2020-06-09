package com.thoughtworks.dddpractice.infrastructure.repository.jpa.order;

import com.thoughtworks.dddpractice.domain.order.Order;
import com.thoughtworks.dddpractice.framework.support.domain.BaseAggregateRootPO;
import com.thoughtworks.dddpractice.infrastructure.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "aggregate_status='ACTIVE'")
public class OrderPO extends BaseAggregateRootPO<Order> {
  @Id
  @Column(name = "id")
  @Setter
  protected String aggregateId;

  private String customerId;

  private BigDecimal freight;

  private double discount;

  private BigDecimal totalAmount;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "order_id")
  private List<OrderItemPO> items;

  @Override
  protected void update(Order order) {
    this.customerId = order.getCustomerId();
    this.items = ObjectMapper.MAPPER.domainToPO(order.getItems());
  }
}

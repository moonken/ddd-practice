package com.thoughtworks.dddpractice.framework.support.infrastructure.repository.jpa;

import com.thoughtworks.dddpractice.framework.support.domain.BaseAggregateRoot;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@EqualsAndHashCode(of = "aggregateId")
@MappedSuperclass
public abstract class BaseAggregateRootPO<D extends BaseAggregateRoot> {

  @Id
  @Column(name = "id")
  @Getter
  protected String aggregateId;

  @Version
  private Long version;

  @Enumerated(EnumType.ORDINAL)
  private BaseAggregateRoot.AggregateStatus aggregateStatus = BaseAggregateRoot.AggregateStatus.ACTIVE;

  public boolean isRemoved() {
    return aggregateStatus == BaseAggregateRoot.AggregateStatus.ARCHIVE;
  }

  public BaseAggregateRootPO updateByDomain(D aggregate) {
    this.aggregateStatus = aggregate.getAggregateStatus();
    this.update(aggregate);
    return this;
  }

  protected abstract void update(D aggregate);
}

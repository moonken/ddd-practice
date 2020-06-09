package com.thoughtworks.dddpractice.framework.support.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class BaseAggregateRootPO<D extends BaseAggregateRoot> {

  @Version
  @Getter
  private Long version;

  @Enumerated(EnumType.STRING)
  private BaseAggregateRoot.AggregateStatus aggregateStatus = BaseAggregateRoot.AggregateStatus.ACTIVE;

  @CreationTimestamp
  @Column(updatable = false)
  @Getter
  private LocalDateTime createdTime;

  @UpdateTimestamp
  @Getter
  private LocalDateTime lastModifiedTime;

  public boolean isRemoved() {
    return aggregateStatus == BaseAggregateRoot.AggregateStatus.ARCHIVE;
  }

  public BaseAggregateRootPO updateByDomain(D aggregate) {
    this.aggregateStatus = aggregate.getAggregateStatus();
    this.update(aggregate);
    return this;
  }

  protected abstract void update(D aggregate);

  @EqualsAndHashCode.Include
  protected abstract String getAggregateId();
}

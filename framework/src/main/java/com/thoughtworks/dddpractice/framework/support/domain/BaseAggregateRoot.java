package com.thoughtworks.dddpractice.framework.support.domain;

import com.thoughtworks.dddpractice.framework.exception.DomainOperationException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public abstract class BaseAggregateRoot {

  @Getter(value = AccessLevel.PACKAGE)
  @Setter(value = AccessLevel.PACKAGE)
  private BaseAggregateRootPO originalPO;

  public enum AggregateStatus {
    ACTIVE, ARCHIVE;
  }

  private Long version;

  private AggregateStatus aggregateStatus = AggregateStatus.ACTIVE;

  public void markAsRemoved() {
    aggregateStatus = AggregateStatus.ARCHIVE;
  }

  public boolean isRemoved() {
    return aggregateStatus == AggregateStatus.ARCHIVE;
  }

  protected void domainError(String message) {
    throw new DomainOperationException(getAggregateId(), message);
  }

  @EqualsAndHashCode.Include
  protected abstract String getAggregateId();
}

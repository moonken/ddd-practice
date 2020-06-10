package com.thoughtworks.dddpractice.framework.support.domain;

import com.thoughtworks.dddpractice.framework.exception.DomainOperationException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode(of = "aggregateId")
@Getter
public abstract class BaseAggregateRoot {

  @Getter(value = AccessLevel.PACKAGE)
  @Setter(value = AccessLevel.PACKAGE)
  private BaseAggregateRootPO originalPO;

  public enum AggregateStatus {
    ACTIVE, ARCHIVE;
  }

  @Getter
  protected String aggregateId;

  private Long version;

  private AggregateStatus aggregateStatus = AggregateStatus.ACTIVE;

  public void markAsRemoved() {
    aggregateStatus = AggregateStatus.ARCHIVE;
  }

  public boolean isRemoved() {
    return aggregateStatus == AggregateStatus.ARCHIVE;
  }

  protected void domainError(String message) {
    throw new DomainOperationException(aggregateId, message);
  }
}

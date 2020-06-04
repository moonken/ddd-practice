package com.thoughtworks.dddpractice.framework.exception;

import lombok.Getter;

public class DomainOperationException extends RuntimeException {

  @Getter
  private String aggregateId;

  public DomainOperationException(String aggregateId, String message) {
    super(message);
    this.aggregateId = aggregateId;
  }
}

package com.thoughtworks.dddpractice.framework.support.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseEntity {

  @EqualsAndHashCode.Include
  protected abstract String getEntityId();
}

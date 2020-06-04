package com.thoughtworks.dddpractice.framework.support.infrastructure.repository.jpa;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(of = "entityId")
@MappedSuperclass
public abstract class BaseEntityPO {

  @Id
  @Column(name = "id")
  @Getter
  private Long entityId;
}

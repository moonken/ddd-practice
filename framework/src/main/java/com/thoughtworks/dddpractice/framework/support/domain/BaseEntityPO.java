package com.thoughtworks.dddpractice.framework.support.domain;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntityPO {

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createdTime;

  @UpdateTimestamp
  private LocalDateTime lastModifiedTime;

  @EqualsAndHashCode.Include
  protected abstract String getEntityId();
}

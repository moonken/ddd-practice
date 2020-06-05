package com.thoughtworks.dddpractice.framework.support.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EqualsAndHashCode(of = "entityId")
@MappedSuperclass
public abstract class BaseEntityPO {

  @Id
  @Column(name = "id")
  @Getter
  private Long entityId;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createdTime;

  @UpdateTimestamp
  private LocalDateTime lastModifiedTime;
}

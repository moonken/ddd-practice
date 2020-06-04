package com.thoughtworks.dddpractice.domain.goods;

import com.thoughtworks.dddpractice.framework.annotations.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@DomainEvent
@AllArgsConstructor
@Data
public class GoodsCreatedEvent implements Serializable {
  private String aggregateId;
}

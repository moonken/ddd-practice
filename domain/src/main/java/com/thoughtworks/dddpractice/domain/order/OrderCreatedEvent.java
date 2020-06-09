package com.thoughtworks.dddpractice.domain.order;

import com.thoughtworks.dddpractice.framework.annotations.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@DomainEvent
@AllArgsConstructor
@Data
public class OrderCreatedEvent implements Serializable {
  private String orderId;
}

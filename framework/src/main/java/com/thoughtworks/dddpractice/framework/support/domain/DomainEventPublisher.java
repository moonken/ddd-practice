package com.thoughtworks.dddpractice.framework.support.domain;

import java.io.Serializable;

public interface DomainEventPublisher {
  void publish(Serializable event);
}

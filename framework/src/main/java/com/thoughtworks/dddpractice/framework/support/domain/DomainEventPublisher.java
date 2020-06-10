package com.thoughtworks.dddpractice.framework.support.domain;

import java.io.Serializable;

public interface DomainEventPublisher {
  void publish(Serializable event);

  class Utils {
    private static DomainEventPublisher instance;

    public static void setInstance(DomainEventPublisher instance) {
      Utils.instance = instance;
    }

    public static void publish(Serializable event) {
      Utils.instance.publish(event);
    }
  }
}

package com.thoughtworks.dddpractice.framework.support.infrastructure.events;

public interface EventHandler {
  boolean canHandle(Object event);

  void handle(Object event);
}

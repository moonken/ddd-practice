package com.thoughtworks.dddpractice.infrastructure.events;

public interface EventHandler {
  boolean canHandle(Object event);

  void handle(Object event);
}

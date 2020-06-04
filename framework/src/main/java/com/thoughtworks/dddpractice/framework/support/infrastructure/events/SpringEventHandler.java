package com.thoughtworks.dddpractice.framework.support.infrastructure.events;

import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;

public class SpringEventHandler implements EventHandler {

  private final Class<?> eventType;
  private final String beanName;
  private final Method method;
  private final ApplicationContext applicationContext;

  public SpringEventHandler(Class<?> eventType, String beanName, Method method, ApplicationContext applicationContext) {
    this.eventType = eventType;
    this.beanName = beanName;
    this.method = method;
    this.applicationContext = applicationContext;
  }

  @Override
  public boolean canHandle(Object event) {
    return eventType.isAssignableFrom(event.getClass());
  }

  @Override
  public void handle(Object event) {
    Object bean = applicationContext.getBean(beanName);
    try {
      method.invoke(bean, event);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

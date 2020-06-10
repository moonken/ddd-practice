package com.thoughtworks.dddpractice.infrastructure.events;

import com.thoughtworks.dddpractice.framework.annotations.event.EventListener;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Component
@Slf4j
public class SimpleEventPublisher implements DomainEventPublisher, BeanPostProcessor {

  private final ApplicationContext applicationContext;

  private Set<EventHandler> eventHandlers = new HashSet<>();

  public SimpleEventPublisher(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
    DomainEventPublisher.Utils.setInstance(this);
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

    for (Method method : bean.getClass().getMethods()) {
      EventListener listenerAnnotation = method.getAnnotation(EventListener.class);

      if (listenerAnnotation == null) {
        continue;
      }

      Class<?> eventType = method.getParameterTypes()[0];
      EventHandler handler = new SpringEventHandler(eventType, beanName, method, applicationContext);
      registerEventHandler(handler);
    }

    return bean;
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }

  public void registerEventHandler(EventHandler handler) {
    eventHandlers.add(handler);
  }

  @Override
  public void publish(Serializable event) {
    doPublish(event);
  }

  protected void doPublish(Object event) {
    for (EventHandler handler : new ArrayList<>(eventHandlers)) {
      if (handler.canHandle(event)) {
        try {
          handler.handle(event);
        } catch (Exception e) {
          log.error("event handling error", e);
        }
      }
    }
  }
}

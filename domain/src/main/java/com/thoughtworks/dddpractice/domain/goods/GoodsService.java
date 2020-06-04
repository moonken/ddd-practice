package com.thoughtworks.dddpractice.domain.goods;

import com.thoughtworks.dddpractice.framework.annotations.domain.DomainService;
import com.thoughtworks.dddpractice.framework.annotations.event.EventListener;
import com.thoughtworks.dddpractice.framework.annotations.event.EventListeners;
import lombok.AllArgsConstructor;

@DomainService
@AllArgsConstructor
@EventListeners
public class GoodsService {

  @EventListener
  public void handle(GoodsCreatedEvent event) {
    System.out.println(event.toString());
  }
}

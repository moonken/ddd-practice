package com.thoughtworks.dddpractice.framework.annotations.event;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

@Inherited
@Component
@Target(ElementType.TYPE)
public @interface EventListeners {

}

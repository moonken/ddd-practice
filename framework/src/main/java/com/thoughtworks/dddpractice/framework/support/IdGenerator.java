package com.thoughtworks.dddpractice.framework.support;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class IdGenerator {
  public static String nextId() {
    return UUID.randomUUID().toString();
  }
}

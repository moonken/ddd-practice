package com.thoughtworks.dddpractice.domain.goods.exception;

import com.thoughtworks.dddpractice.framework.annotations.domain.DomainException;

@DomainException
public class GoodsNameTooLongException extends RuntimeException {
  public GoodsNameTooLongException(String name) {
    super("Goods name " + name + " too long");
  }
}

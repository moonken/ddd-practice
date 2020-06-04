package com.thoughtworks.dddpractice.domain.goods.exception;

import com.thoughtworks.dddpractice.framework.annotations.domain.DomainException;

@DomainException
public class PriceLessThanZeroException extends RuntimeException {
  public PriceLessThanZeroException(Double price) {
    super("Goods price " + price + " is less than 0");
  }
}

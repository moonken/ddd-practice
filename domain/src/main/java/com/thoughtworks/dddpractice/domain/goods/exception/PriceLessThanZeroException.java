package com.thoughtworks.dddpractice.domain.goods.exception;

import com.thoughtworks.dddpractice.framework.annotations.domain.DomainException;

import java.math.BigDecimal;

@DomainException
public class PriceLessThanZeroException extends RuntimeException {
  public PriceLessThanZeroException(BigDecimal price) {
    super("Goods price " + price + " is less than 0");
  }
}

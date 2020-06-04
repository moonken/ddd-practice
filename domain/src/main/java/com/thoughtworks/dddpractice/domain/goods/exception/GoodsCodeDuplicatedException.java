package com.thoughtworks.dddpractice.domain.goods.exception;

import com.thoughtworks.dddpractice.framework.annotations.domain.DomainException;

@DomainException
public class GoodsCodeDuplicatedException extends RuntimeException {
  public GoodsCodeDuplicatedException(String code) {
    super("Goods code :" + code + " already exist");
  }
}

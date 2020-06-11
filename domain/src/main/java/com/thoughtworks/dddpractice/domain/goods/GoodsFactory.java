package com.thoughtworks.dddpractice.domain.goods;

import com.thoughtworks.dddpractice.domain.goods.exception.GoodsCodeDuplicatedException;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainFactory;
import com.thoughtworks.dddpractice.framework.annotations.domain.Invariant;
import com.thoughtworks.dddpractice.framework.annotations.domain.Invariants;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Invariants({
  "duplicates: code can not duplicates",
})
@DomainFactory
@AllArgsConstructor
public class GoodsFactory {

  private final GoodsRepository goodsRepository;

  @Invariant("duplicates")
  public Goods create(String code, String name, BigDecimal price) {
    if (goodsRepository.findByCode(code).isPresent()) {
      throw new GoodsCodeDuplicatedException(code);
    }

    return new Goods(code, name, price);
  }

  public Goods load(String aggregateId, String code, String name, BigDecimal price) {
    return new Goods(aggregateId, code, name, price);
  }

}

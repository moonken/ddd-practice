package com.thoughtworks.dddpractice.domain.goods;

import com.thoughtworks.dddpractice.domain.goods.dto.GoodsDTO;
import com.thoughtworks.dddpractice.domain.goods.exception.GoodsCodeDuplicatedException;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainFactory;
import com.thoughtworks.dddpractice.framework.annotations.domain.Invariant;
import com.thoughtworks.dddpractice.framework.annotations.domain.Invariants;
import com.thoughtworks.dddpractice.framework.support.domain.DomainEventPublisher;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Invariants({
  "duplicates: code can not duplicates",
})
@DomainFactory
@AllArgsConstructor
public class GoodsFactory {

  private final DomainEventPublisher domainEventPublisher;
  private final GoodsRepository goodsRepository;

  @Invariant("duplicates")
  public Goods create(GoodsDTO goodsDTO) {
    if (goodsRepository.findByCode(goodsDTO.getCode()).isPresent()) {
      throw new GoodsCodeDuplicatedException(goodsDTO.getCode());
    }

    return new Goods(UUID.randomUUID().toString(), goodsDTO, domainEventPublisher);
  }

  public Goods load(GoodsDTO goodsDTO) {
    return new Goods(goodsDTO, domainEventPublisher);
  }

}

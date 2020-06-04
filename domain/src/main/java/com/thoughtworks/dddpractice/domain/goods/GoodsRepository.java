package com.thoughtworks.dddpractice.domain.goods;

import com.thoughtworks.dddpractice.framework.annotations.domain.DomainRepository;
import com.thoughtworks.dddpractice.framework.support.domain.GenericDomainRepository;

import java.util.Optional;

@DomainRepository
public interface GoodsRepository extends GenericDomainRepository<Goods> {
  Optional<Goods> findByCode(String code);
}

package com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods;

import com.thoughtworks.dddpractice.domain.goods.Goods;
import com.thoughtworks.dddpractice.domain.goods.GoodsFactory;
import com.thoughtworks.dddpractice.domain.goods.GoodsRepository;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainRepositoryImpl;
import com.thoughtworks.dddpractice.framework.support.domain.GenericDomainRepositoryImpl;
import com.thoughtworks.dddpractice.infrastructure.ObjectMapper;
import org.springframework.context.annotation.Lazy;

import java.util.Optional;

@DomainRepositoryImpl
public class GoodsRepositoryImpl extends GenericDomainRepositoryImpl<Goods, GoodsPO> implements GoodsRepository {
  private final GoodsFactory goodsFactory;

  public GoodsRepositoryImpl(@Lazy GoodsFactory goodsFactory) {
    this.goodsFactory = goodsFactory;
  }

  @Override
  protected Goods poToDomain(GoodsPO goodsPO) {
    return goodsFactory.load(goodsPO.getAggregateId(), goodsPO.getCode(), goodsPO.getName(), goodsPO.getPrice());
  }

  @Override
  protected GoodsPO domainToPO(Goods goods) {
    GoodsPO goodsPO = ObjectMapper.MAPPER.domainToPO(goods);
    return goodsPO;
  }

  @Override
  public Optional<Goods> findByCode(String code) {
    return ((JpaGoodsRepository) jpaRepository).findByCode(code)
      .map(this::poToDomain);
  }
}

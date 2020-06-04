package com.thoughtworks.dddpractice.representation.vo;

import com.thoughtworks.dddpractice.domain.goods.Goods;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods.GoodsPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedSourcePolicy = IGNORE)
public interface GoodsMapper {

  GoodsMapper MAPPER = Mappers.getMapper(GoodsMapper.class);

  GoodsVO toVO(Goods goods);

  GoodsVO toVO(GoodsPO goods);

  List<GoodsVO> toVOs(List<GoodsPO> goods);
}

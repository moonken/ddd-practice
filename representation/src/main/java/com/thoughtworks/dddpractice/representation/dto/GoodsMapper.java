package com.thoughtworks.dddpractice.representation.dto;

import com.thoughtworks.dddpractice.domain.goods.Goods;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods.GoodsPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedSourcePolicy = IGNORE, unmappedTargetPolicy = IGNORE)
public interface GoodsMapper {

  GoodsMapper MAPPER = Mappers.getMapper(GoodsMapper.class);

  GoodsDTO toDTO(Goods goods);

  GoodsDTO toDTO(GoodsPO goods);

  List<GoodsDTO> toDTOs(List<GoodsPO> goods);
}

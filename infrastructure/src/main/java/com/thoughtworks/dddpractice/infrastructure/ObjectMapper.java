package com.thoughtworks.dddpractice.infrastructure;

import com.thoughtworks.dddpractice.domain.goods.dto.GoodsDTO;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods.GoodsPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedSourcePolicy = IGNORE, unmappedTargetPolicy = IGNORE)
public interface ObjectMapper {

  ObjectMapper MAPPER = Mappers.getMapper(ObjectMapper.class);

  GoodsDTO poToVO(GoodsPO goods);
}

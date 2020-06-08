package com.thoughtworks.dddpractice.application;

import com.thoughtworks.dddpractice.application.command.GoodsCreateCommand;
import com.thoughtworks.dddpractice.domain.goods.dto.GoodsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedSourcePolicy = IGNORE, unmappedTargetPolicy = IGNORE)
public interface ObjectMapper {

  ObjectMapper MAPPER = Mappers.getMapper(ObjectMapper.class);

  GoodsDTO commandToDTO(GoodsCreateCommand goodsCreateCommand);
}

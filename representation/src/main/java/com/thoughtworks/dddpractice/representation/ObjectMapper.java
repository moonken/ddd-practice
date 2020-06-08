package com.thoughtworks.dddpractice.representation;

import com.thoughtworks.dddpractice.application.command.GoodsCreateCommand;
import com.thoughtworks.dddpractice.representation.restful.request.GoodsCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedSourcePolicy = IGNORE, unmappedTargetPolicy = IGNORE)
public interface ObjectMapper {

  ObjectMapper MAPPER = Mappers.getMapper(ObjectMapper.class);

  GoodsCreateCommand toCommand(GoodsCreateRequest request);
}

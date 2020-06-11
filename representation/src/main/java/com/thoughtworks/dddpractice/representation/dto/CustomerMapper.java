package com.thoughtworks.dddpractice.representation.dto;

import com.thoughtworks.dddpractice.infrastructure.repository.jpa.customer.goods.CustomerPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedSourcePolicy = IGNORE, unmappedTargetPolicy = IGNORE)
public interface CustomerMapper {

  CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

  CustomerDTO poToDTO(CustomerPO customers);

  List<CustomerDTO> poToDTOs(List<CustomerPO> customers);

}

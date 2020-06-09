package com.thoughtworks.dddpractice.representation.restful.controllers;

import com.thoughtworks.dddpractice.application.service.CustomerReadService;
import com.thoughtworks.dddpractice.domain.customer.dto.CustomerDTO;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.customer.goods.CustomerPO;
import com.thoughtworks.dddpractice.representation.dto.CustomerMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("customers")
@AllArgsConstructor
public class CustomerController {
  private final CustomerReadService customerReadService;

  @GetMapping
  public List<CustomerDTO> getAll() {
    List<CustomerPO> customers = customerReadService.getAllPO();
    return CustomerMapper.MAPPER.poToDTOs(customers);
  }

}

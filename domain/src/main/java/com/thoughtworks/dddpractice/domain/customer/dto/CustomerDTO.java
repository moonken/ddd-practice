package com.thoughtworks.dddpractice.domain.customer.dto;

import com.thoughtworks.dddpractice.framework.annotations.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CustomerDTO {
  private String aggregateId;
  private String name;
  private boolean vip;
}

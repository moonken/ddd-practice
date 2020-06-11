package com.thoughtworks.dddpractice.representation.dto;

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

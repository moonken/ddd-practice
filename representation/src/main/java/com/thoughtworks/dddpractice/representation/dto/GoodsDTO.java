package com.thoughtworks.dddpractice.representation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDTO {
  private String aggregateId;
  private String code;
  private String name;
  private Double price;
}

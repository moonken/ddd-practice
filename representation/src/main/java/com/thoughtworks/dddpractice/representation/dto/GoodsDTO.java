package com.thoughtworks.dddpractice.representation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GoodsDTO {
  private String aggregateId;
  private String code;
  private String name;
  private BigDecimal price;
}

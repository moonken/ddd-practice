package com.thoughtworks.dddpractice.representation.restful.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsCreateRequest {
  @NotNull
  private String code;

  @NotNull
  private String name;

  @NotNull
  private Double price;
}

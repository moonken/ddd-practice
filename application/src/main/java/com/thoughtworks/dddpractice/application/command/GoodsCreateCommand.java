package com.thoughtworks.dddpractice.application.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsCreateCommand {
  private String code;
  private String name;
  private Double price;
}
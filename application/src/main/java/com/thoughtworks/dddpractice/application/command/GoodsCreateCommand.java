package com.thoughtworks.dddpractice.application.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoodsCreateCommand {
  private String code;
  private String name;
  private Double price;
}

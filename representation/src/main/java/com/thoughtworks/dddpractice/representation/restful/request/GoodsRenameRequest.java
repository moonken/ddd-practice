package com.thoughtworks.dddpractice.representation.restful.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GoodsRenameRequest {
  @NotNull
  private String newName;
}

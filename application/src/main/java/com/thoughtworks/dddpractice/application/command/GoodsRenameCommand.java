package com.thoughtworks.dddpractice.application.command;

import lombok.Data;

@Data
public class GoodsRenameCommand {
  private String id;
  private String newName;
}

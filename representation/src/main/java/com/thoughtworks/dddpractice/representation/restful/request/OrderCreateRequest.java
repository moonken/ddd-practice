package com.thoughtworks.dddpractice.representation.restful.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderCreateRequest {
  private String customerId;
  private List<OrderItem> items;

  @Data
  public static class OrderItem {
    private String goodsId;
    private Double quality;
    private double discount;
  }
}

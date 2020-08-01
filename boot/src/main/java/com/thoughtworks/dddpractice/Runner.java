package com.thoughtworks.dddpractice;

import com.thoughtworks.dddpractice.infrastructure.repository.jpa.customer.goods.CustomerPO;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.customer.goods.JpaCustomerRepository;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods.GoodsPO;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods.JpaGoodsRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class Runner implements CommandLineRunner {

  private final JpaGoodsRepository jpaGoodsRepository;
  private final JpaCustomerRepository jpaCustomerRepository;

  @Override
  public void run(String... args) {
    IntStream.range(1, 101)
      .forEach(i ->
      {
        String id = "G" + i;
        if (!jpaGoodsRepository.findById(id).isPresent()) {
          GoodsPO goodsPO = GoodsPO.builder()
            .code(id)
            .name("Goods" + i)
            .price(BigDecimal.valueOf(Math.round(Math.random() * 1000) / 10d)).build();
          goodsPO.setAggregateId(id);
          jpaGoodsRepository.save(goodsPO);
        }
      });

    IntStream.range(1, 6)
      .forEach(i ->
      {
        String id = "C" + i;
        if (!jpaCustomerRepository.findById(id).isPresent()) {
          CustomerPO customerPO = CustomerPO.builder()
            .name("Customer" + i)
            .vip(false).build();
          customerPO.setAggregateId(id);
          jpaCustomerRepository.save(customerPO);
        }
      });
  }


}

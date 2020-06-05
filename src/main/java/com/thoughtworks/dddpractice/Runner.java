package com.thoughtworks.dddpractice;

import com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods.GoodsPO;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods.JpaGoodsRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class Runner implements CommandLineRunner {

  private final JpaGoodsRepository jpaGoodsRepository;

  @Override
  public void run(String... args) {
    IntStream.range(1, 101)
      .forEach(i ->
      {
        String id = String.valueOf(i);
        if (!jpaGoodsRepository.findById(id).isPresent()) {
          GoodsPO goodsPO = GoodsPO.builder()
            .code(id)
            .name("Goods" + i)
            .price(Math.round(Math.random() * 1000) / 10d).build();
          goodsPO.setAggregateId(id);
          jpaGoodsRepository.save(goodsPO);
        }
      });
  }
}

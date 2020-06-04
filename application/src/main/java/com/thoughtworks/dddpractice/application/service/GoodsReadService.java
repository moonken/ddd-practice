package com.thoughtworks.dddpractice.application.service;

import com.thoughtworks.dddpractice.application.exception.ResourceNotFoundException;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods.GoodsPO;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods.JpaGoodsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GoodsReadService {
  private final JpaGoodsRepository jpaGoodsRepository;

  public GoodsPO getPO(String id) {
    return jpaGoodsRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Goods id " + id + "not found"));
  }

  public List<GoodsPO> getAllPO() {
    return jpaGoodsRepository.findAll();
  }
}

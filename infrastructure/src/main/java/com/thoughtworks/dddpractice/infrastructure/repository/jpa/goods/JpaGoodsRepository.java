package com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaGoodsRepository extends JpaRepository<GoodsPO, String> {
  Optional<GoodsPO> findByCode(String code);
}

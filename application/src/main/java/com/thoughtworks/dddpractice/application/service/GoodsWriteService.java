package com.thoughtworks.dddpractice.application.service;

import com.thoughtworks.dddpractice.application.ObjectMapper;
import com.thoughtworks.dddpractice.application.command.GoodsCreateCommand;
import com.thoughtworks.dddpractice.domain.goods.Goods;
import com.thoughtworks.dddpractice.domain.goods.GoodsFactory;
import com.thoughtworks.dddpractice.domain.goods.GoodsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GoodsWriteService {

  private final GoodsFactory goodsFactory;
  private final GoodsRepository goodsRepository;

  public Goods create(GoodsCreateCommand command) {
    return goodsFactory.create(ObjectMapper.MAPPER.commandToVO(command));
  }

  public void rename(String id, String name) {
    Goods goods = goodsRepository.load(id);
    goods.rename(name);
    goodsRepository.save(goods);
  }

  public void delete(String id) {
    goodsRepository.delete(id);
  }
}

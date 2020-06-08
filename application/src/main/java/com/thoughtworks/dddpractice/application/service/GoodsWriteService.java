package com.thoughtworks.dddpractice.application.service;

import com.thoughtworks.dddpractice.application.ObjectMapper;
import com.thoughtworks.dddpractice.application.command.GoodsCreateCommand;
import com.thoughtworks.dddpractice.domain.goods.Goods;
import com.thoughtworks.dddpractice.domain.goods.GoodsRepository;
import com.thoughtworks.dddpractice.domain.goods.GoodsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class GoodsWriteService {

  private final GoodsService goodsService;
  private final GoodsRepository goodsRepository;

  public Goods create(GoodsCreateCommand command) {
    return goodsService.create(ObjectMapper.MAPPER.commandToDTO(command));
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

package com.thoughtworks.dddpractice.representation.restful.controllers;

import com.thoughtworks.dddpractice.application.command.GoodsCreateCommand;
import com.thoughtworks.dddpractice.application.command.GoodsRenameCommand;
import com.thoughtworks.dddpractice.application.service.GoodsReadService;
import com.thoughtworks.dddpractice.application.service.GoodsWriteService;
import com.thoughtworks.dddpractice.domain.goods.Goods;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods.GoodsPO;
import com.thoughtworks.dddpractice.representation.vo.GoodsMapper;
import com.thoughtworks.dddpractice.representation.vo.GoodsVO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("goods")
@AllArgsConstructor
public class GoodsController {
  private final GoodsWriteService goodsWriteService;
  private final GoodsReadService goodsReadService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GoodsVO create(@RequestBody @Valid GoodsCreateCommand command) {
    Goods goods = goodsWriteService.create(command.getCode(), command.getName(), command.getPrice());
    return GoodsMapper.MAPPER.toVO(goods);
  }

  @PutMapping("{id}")
  public void rename(@RequestBody @Valid GoodsRenameCommand command, @PathVariable String id) {
    goodsWriteService.rename(id, command.getNewName());
  }

  @GetMapping("{id}")
  public GoodsVO get(@PathVariable String id) {
    GoodsPO goods = goodsReadService.getPO(id);
    return GoodsMapper.MAPPER.toVO(goods);
  }

  @GetMapping
  public List<GoodsVO> getAll() {
    List<GoodsPO> goods = goodsReadService.getAllPO();
    return GoodsMapper.MAPPER.toVOs(goods);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable String id) {
    goodsWriteService.delete(id);
  }
}

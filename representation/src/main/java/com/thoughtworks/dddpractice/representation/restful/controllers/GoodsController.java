package com.thoughtworks.dddpractice.representation.restful.controllers;

import com.thoughtworks.dddpractice.application.command.GoodsCreateCommand;
import com.thoughtworks.dddpractice.application.command.GoodsRenameCommand;
import com.thoughtworks.dddpractice.application.service.GoodsReadService;
import com.thoughtworks.dddpractice.application.service.GoodsWriteService;
import com.thoughtworks.dddpractice.domain.goods.Goods;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods.GoodsPO;
import com.thoughtworks.dddpractice.representation.dto.GoodsDTO;
import com.thoughtworks.dddpractice.representation.dto.GoodsMapper;
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
  public GoodsDTO create(@RequestBody @Valid GoodsCreateCommand command) {
    Goods goods = goodsWriteService.create(command);
    return GoodsMapper.MAPPER.toDTO(goods);
  }

  @PutMapping("{id}")
  public void rename(@RequestBody @Valid GoodsRenameCommand command, @PathVariable String id) {
    goodsWriteService.rename(id, command.getNewName());
  }

  @GetMapping("{id}")
  public GoodsDTO get(@PathVariable String id) {
    GoodsPO goods = goodsReadService.getPO(id);
    return GoodsMapper.MAPPER.toDTO(goods);
  }

  @GetMapping
  public List<GoodsDTO> getAll() {
    List<GoodsPO> goods = goodsReadService.getAllPO();
    return GoodsMapper.MAPPER.toDTOs(goods);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable String id) {
    goodsWriteService.delete(id);
  }
}

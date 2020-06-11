package com.thoughtworks.dddpractice.infrastructure;

import com.thoughtworks.dddpractice.domain.customer.Customer;
import com.thoughtworks.dddpractice.domain.goods.Goods;
import com.thoughtworks.dddpractice.domain.order.GoodsSnapshot;
import com.thoughtworks.dddpractice.domain.order.Order;
import com.thoughtworks.dddpractice.domain.order.OrderItem;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.customer.goods.CustomerPO;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.goods.GoodsPO;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.order.GoodsSnapshotPO;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.order.OrderItemPO;
import com.thoughtworks.dddpractice.infrastructure.repository.jpa.order.OrderPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedSourcePolicy = IGNORE, unmappedTargetPolicy = IGNORE)
public interface ObjectMapper {

  ObjectMapper MAPPER = Mappers.getMapper(ObjectMapper.class);

  GoodsPO domainToPO(Goods goods);

  CustomerPO domainToPO(Customer customer);

  GoodsSnapshot poToDTO(GoodsSnapshotPO goodsSnapshotPO);

  OrderPO domainToPO(Order order);

  OrderItemPO domainToPO(OrderItem orderItem);

  GoodsSnapshotPO domainToPO(GoodsSnapshot goodsSnapshot);

  List<OrderItemPO> domainToPO(List<OrderItem> orderItem);
}

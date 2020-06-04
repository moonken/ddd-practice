package com.thoughtworks.dddpractice.framework.support.domain;

public interface GenericDomainRepository<D extends BaseAggregateRoot> {
  D load(String aggregateId);

  void save(D aggregate);

  void delete(String aggregateId);
}

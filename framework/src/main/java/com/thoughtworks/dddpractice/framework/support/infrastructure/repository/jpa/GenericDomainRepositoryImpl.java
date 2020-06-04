package com.thoughtworks.dddpractice.framework.support.infrastructure.repository.jpa;

import com.thoughtworks.dddpractice.framework.support.domain.BaseAggregateRoot;
import com.thoughtworks.dddpractice.framework.support.domain.GenericDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;

public abstract class GenericDomainRepositoryImpl<D extends BaseAggregateRoot, P extends BaseAggregateRootPO>
  implements GenericDomainRepository<D> {

  @Autowired
  protected JpaRepository<P, String> jpaRepository;

  private Class<P> poClazz;

  public GenericDomainRepositoryImpl() {
    this.poClazz = ((Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
  }

  @Override
  @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
  public D load(String id) {
    P aggregatePO =
      jpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Aggregate " + poClazz.getCanonicalName() +
        " id = " + id + " does not exist"));

    if (aggregatePO.isRemoved()) {
      throw new RuntimeException("Aggragate " + id + " is removed.");
    }

    D aggregate = poToDomain(aggregatePO);

    return aggregate;
  }

  protected abstract D poToDomain(P aggregatePO);

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void save(D aggregate) {
    P aggregatePO = (P) jpaRepository.findById(aggregate.getAggregateId())
      .map(p -> p.updateByDomain(aggregate))
      .orElse(domainToPO(aggregate));
    jpaRepository.save(aggregatePO);
  }

  protected abstract P domainToPO(D aggregate);

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void delete(String id) {
    D aggregate = load(id);
    aggregate.markAsRemoved();
    save(aggregate);
  }
}

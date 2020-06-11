package com.thoughtworks.dddpractice.infrastructure.repository.jpa.customer.goods;

import com.thoughtworks.dddpractice.domain.customer.Customer;
import com.thoughtworks.dddpractice.domain.customer.CustomerFactory;
import com.thoughtworks.dddpractice.domain.customer.CustomerRepository;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainRepositoryImpl;
import com.thoughtworks.dddpractice.framework.support.domain.GenericDomainRepositoryImpl;
import com.thoughtworks.dddpractice.infrastructure.ObjectMapper;
import org.springframework.context.annotation.Lazy;

@DomainRepositoryImpl
public class CustomerRepositoryImpl extends GenericDomainRepositoryImpl<Customer, CustomerPO> implements CustomerRepository {
  private final CustomerFactory customerFactory;

  public CustomerRepositoryImpl(@Lazy CustomerFactory customerFactory) {
    this.customerFactory = customerFactory;
  }

  @Override
  protected Customer poToDomain(CustomerPO customerPO) {
    return customerFactory.load(customerPO.getAggregateId(), customerPO.getName(), customerPO.isVip());
  }

  @Override
  protected CustomerPO domainToPO(Customer customer) {
    return ObjectMapper.MAPPER.domainToPO(customer);
  }
}

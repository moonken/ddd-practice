package com.thoughtworks.dddpractice.domain.customer;

import com.thoughtworks.dddpractice.framework.annotations.domain.DomainRepository;
import com.thoughtworks.dddpractice.framework.support.domain.GenericDomainRepository;

import java.util.Optional;

@DomainRepository
public interface CustomerRepository extends GenericDomainRepository<Customer> {
}

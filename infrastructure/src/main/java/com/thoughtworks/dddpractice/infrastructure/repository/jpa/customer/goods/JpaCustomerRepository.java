package com.thoughtworks.dddpractice.infrastructure.repository.jpa.customer.goods;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCustomerRepository extends JpaRepository<CustomerPO, String> {
}

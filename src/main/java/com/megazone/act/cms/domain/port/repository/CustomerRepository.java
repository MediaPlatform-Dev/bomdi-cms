package com.megazone.act.cms.domain.port.repository;

import com.megazone.act.cms.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}

package com.megazone.act.cms.domain.repository;

import com.megazone.act.cms.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}

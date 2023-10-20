package com.megazone.act.cms.domain.repository;

import com.megazone.act.cms.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessPartnerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByName(String name);
}

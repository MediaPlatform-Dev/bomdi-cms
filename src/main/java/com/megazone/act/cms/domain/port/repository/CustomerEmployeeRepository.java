package com.megazone.act.cms.domain.port.repository;

import com.megazone.act.cms.domain.entity.CustomerEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerEmployeeRepository extends JpaRepository<CustomerEmployee, Integer> {
}

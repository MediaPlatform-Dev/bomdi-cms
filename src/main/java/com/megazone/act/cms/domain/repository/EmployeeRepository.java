package com.megazone.act.cms.domain.repository;

import com.megazone.act.cms.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
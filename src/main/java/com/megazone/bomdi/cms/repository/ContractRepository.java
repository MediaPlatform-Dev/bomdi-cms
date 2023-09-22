package com.megazone.bomdi.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megazone.bomdi.cms.domain.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}

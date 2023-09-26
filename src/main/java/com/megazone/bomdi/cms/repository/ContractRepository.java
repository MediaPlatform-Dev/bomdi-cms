package com.megazone.bomdi.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megazone.bomdi.cms.domain.Contract;
import org.springframework.data.repository.history.RevisionRepository;

public interface ContractRepository extends JpaRepository<Contract, Long>, RevisionRepository<Contract, Long, Long> {
}

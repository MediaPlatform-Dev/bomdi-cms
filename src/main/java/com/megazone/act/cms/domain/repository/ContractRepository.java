package com.megazone.act.cms.domain.repository;

import com.megazone.act.cms.domain.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long>, RevisionRepository<Contract, Long, Long> {

    Optional<Contract> findById(Long contractId);
}

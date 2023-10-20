package com.megazone.act.cms.domain.repository;

import com.megazone.act.cms.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long>, RevisionRepository<Contract, Long, Long> {

    @Override
    @Query("""
        select contract from Contract contract
        join fetch contract.customer
        join fetch contract.corporation
        where contract.id = :contractId
    """)
    Optional<Contract> findById(@Param("contractId") Long contractId);
}

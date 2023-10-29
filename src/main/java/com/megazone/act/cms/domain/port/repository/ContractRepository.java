package com.megazone.act.cms.domain.port.repository;

import com.megazone.act.cms.domain.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface ContractRepository extends JpaRepository<Contract, Long>,
    RevisionRepository<Contract, Long, Long>,
    ContractRepositoryCustom {

}
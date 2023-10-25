package com.megazone.act.cms.domain.repository;

import com.megazone.act.cms.domain.entity.Contract;

import java.util.List;

public interface ContractRepositoryCustom {

    List<Contract> findAllQuery();
}

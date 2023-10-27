package com.megazone.act.cms.domain.repository;

import com.megazone.act.cms.domain.dto.condition.ContractCondition;
import com.megazone.act.cms.domain.dto.query.ContractSimpleQuery;

import java.util.List;

public interface ContractRepositoryCustom {


    List<ContractSimpleQuery> findAllQuery(ContractCondition condition);
}

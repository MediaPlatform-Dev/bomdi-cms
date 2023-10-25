package com.megazone.act.cms.domain.repository;

import com.megazone.act.cms.domain.entity.Contract;
import com.megazone.act.cms.domain.entity.QContract;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ContractRepositoryCustomImpl extends QuerydslRepositorySupport implements ContractRepositoryCustom {

    public ContractRepositoryCustomImpl() {
        super(Contract.class);
    }

    // TODO: 네이밍 필요..
    @Override
    public List<Contract> findAllQuery() {
        QContract contract = QContract.contract;
        return from(contract)
            .select(contract)
            .fetch();
    }
}

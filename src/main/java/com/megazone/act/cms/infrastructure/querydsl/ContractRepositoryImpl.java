package com.megazone.act.cms.infrastructure.querydsl;

import com.megazone.act.cms.domain.dto.ContractCondition;
import com.megazone.act.cms.domain.entity.Contract;
import com.megazone.act.cms.domain.repository.ContractRepositoryCustom;
import com.megazone.act.cms.domain.type.ContractDetailType;
import com.megazone.act.cms.domain.type.ContractType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.megazone.act.cms.domain.entity.QContract.contract;
import static com.megazone.act.cms.domain.entity.QContractDetail.contractDetail;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@Repository
public class ContractRepositoryImpl implements ContractRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Contract> findAllQuery() {
        return queryFactory.selectFrom(contract).fetch();
    }

    @Override
    public List<Contract> findAllQuery(ContractCondition condition) {
        return queryFactory.selectFrom(contract)
            .innerJoin(contract.contractDetails, contractDetail)
            .where(contractTypeEq(condition.contractType()))
            .where(contractDetailTypeEq(condition.detailType()))
            .where(contractNoEq(condition.contractNo()))
            .fetch();
    }

    private BooleanExpression contractTypeEq(ContractType type) {
        return type != null ? contract.contractTypes.contractType.eq(type) : null;
    }

    private BooleanExpression contractDetailTypeEq(ContractDetailType type) {
        return type != null ? contractDetail.contractDetailType.eq(type) : null;
    }

    private BooleanExpression contractNoEq(String no) {
        return hasText(no) ? contract.no.eq(no) : null;
    }
}

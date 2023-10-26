package com.megazone.act.cms.infrastructure.querydsl;

import com.megazone.act.cms.domain.dto.ContractCondition;
import com.megazone.act.cms.domain.entity.Contract;
import com.megazone.act.cms.domain.repository.ContractRepositoryCustom;
import com.megazone.act.cms.domain.type.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.megazone.act.cms.domain.entity.QContract.contract;
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
            .innerJoin(contract.contractDetails)
            .innerJoin(contract.contractEmployees)
            .where(contractTypeEq(condition.contractType()))
            .where(contractDetailTypeEq(condition.detailTypes()))
            .where(contractManagerNameEq(condition.contractManagerName()))
            .where(contractSalesMangerNameEq(condition.contractSalesManagerName()))
            .where(contractNameContains(condition.contractName()))
            .where(statusEq(condition.status()))
            .fetch();
    }

    private BooleanExpression contractTypeEq(ContractType type) {
        return type != null ? contract.contractTypes.contractType.eq(type) : null;
    }

    private BooleanExpression contractDetailTypeEq(List<ContractDetailType> types) {
        return (types != null && !types.isEmpty()) ? contract.contractDetails.any().contractDetailType.in(types) : null;
    }

    private BooleanExpression contractNameContains(String name) {
        return hasText(name) ? contract.name.contains(name) : null;
    }

    private BooleanExpression contractManagerNameEq(String name) {
        return hasText(name) ? contract.contractEmployees.any().employee.name.eq(name) : null;
    }

    private BooleanExpression contractSalesMangerNameEq(String name) {
        return hasText(name) ? contract.contractEmployees.any().employee.name.eq(name) : null;
    }

    private BooleanExpression statusEq(ContractStatus status) {
        if (status == ContractStatus.ALL) {
            return null;
        }

        return status != null ? contract.status.eq(status) : null;
    }
}

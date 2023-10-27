package com.megazone.act.cms.infrastructure.querydsl;

import com.megazone.act.cms.domain.dto.condition.ContractCondition;
import com.megazone.act.cms.domain.dto.query.*;
import com.megazone.act.cms.domain.repository.ContractRepositoryCustom;
import com.megazone.act.cms.domain.type.*;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.megazone.act.cms.domain.entity.QContract.contract;
import static com.megazone.act.cms.domain.entity.QContractDetail.contractDetail;
import static com.megazone.act.cms.domain.entity.QContractEmployee.contractEmployee;
import static com.querydsl.core.types.dsl.Expressions.as;
import static org.springframework.util.StringUtils.hasText;

@Slf4j
@RequiredArgsConstructor
@Repository
public class ContractRepositoryImpl implements ContractRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ContractSimpleQuery> findAllQuery(ContractCondition condition) {
        List<ContractSimpleQuery> contracts = getContracts(condition);
        List<Integer> contractIds = contracts.stream().map(ContractSimpleQuery::getId).toList();

        Map<Integer, List<ContractEmployeeQuery>> contractEmployees = getContractEmployees(condition, contractIds)
            .stream()
            .collect(Collectors.groupingBy(ContractEmployeeQuery::getContractId));

        Map<Integer, List<ContractDetailQuery>> contractDetails = getContractDetails(condition, contractIds)
            .stream()
            .collect(Collectors.groupingBy(ContractDetailQuery::getContractId));

        contracts.forEach(
            it -> {
                it.addEmployees(contractEmployees.get(it.getId()));
                it.addDetail(contractDetails.get(it.getId()));
            }
        );
        return contracts;
    }

    private List<ContractSimpleQuery> getContracts(ContractCondition condition) {
        return queryFactory.select(Projections.fields(ContractSimpleQuery.class,
                    contract.id,
                    contract.status,
                    contract.contractTypes.contractType,
                    contract.name,
                    contract.salesForceContractNo,
                    contract.contractMoney.amount,
                    contract.contractPeriod.startDate,
                    contract.contractPeriod.endDate,
                    as(contract.customer.name, "customerName")
                )
            )
            .from(contract)
            .join(contract.customer)
            .where(contractTypeEq(condition.contractType()))
            .where(contractNameContains(condition.contractName()))
            .where(statusEq(condition.status()))
            .where(detailTypeIn(condition))
            .where(employeeNamesIn(condition))
            .fetch();
    }

    private static BooleanExpression detailTypeIn(ContractCondition condition) {
        List<ContractDetailType> detailTypes = condition.detailTypes();
        if (detailTypes == null || detailTypes.isEmpty()) {
            return null;
        }

        return contract.id.in(
            JPAExpressions
                .select(contractDetail.contract.id)
                .from(contractDetail)
                .where(contractDetail.contractDetailType.in(detailTypes))
        );
    }

    private static BooleanExpression employeeNamesIn(ContractCondition condition) {
        return contract.id.in(
            JPAExpressions
                .select(contractEmployee.contract.id)
                .from(contractEmployee)
                .join(contractEmployee.employee)
                .where(contractManagerNameEq(condition))
                .where(salesManagerNameEq(condition))
        );
    }

    private static BooleanExpression contractManagerNameEq(ContractCondition condition) {
        String salesManagerName = condition.contractSalesManagerName();
        if (!hasText(salesManagerName)) {
            return null;
        }
        return contractEmployee.employee.name.eq(salesManagerName).and(contractEmployee.type.eq(EmployeeRoleType.SALES));
    }

    private static BooleanExpression salesManagerNameEq(ContractCondition condition) {
        String contractManagerName = condition.contractManagerName();
        if (!hasText(contractManagerName)) {
            return null;
        }
        return contractEmployee.employee.name.eq(contractManagerName).and(contractEmployee.type.eq(EmployeeRoleType.CONTRACT));
    }

    private List<ContractEmployeeQuery> getContractEmployees(ContractCondition condition, List<Integer> contractIds) {
        return queryFactory.select(Projections.fields(ContractEmployeeQuery.class,
                    as(contractEmployee.contract.id, "contractId"),
                    contractEmployee.employee.name,
                    contractEmployee.type
                )
            )
            .from(contractEmployee)
            .join(contractEmployee.employee)
            .where(contractEmployee.contract.id.in(contractIds))
            .fetch();
    }

    private List<ContractDetailQuery> getContractDetails(ContractCondition condition, List<Integer> contractIds) {
        return queryFactory.select(Projections.fields(ContractDetailQuery.class,
                    as(contract.id, "contractId"),
                    as(contractDetail.contractDetailType, "type")
                )
            )
            .from(contractDetail)
            .where(contractDetail.contract.id.in(contractIds))
            .fetch();
    }

    private BooleanExpression contractTypeEq(ContractType type) {
        return type != null ? contract.contractTypes.contractType.eq(type) : null;
    }

    private BooleanExpression contractNameContains(String name) {
        return hasText(name) ? contract.name.contains(name) : null;
    }

    private BooleanExpression statusEq(ContractStatus status) {
        if (status == ContractStatus.ALL) {
            return null;
        }

        return status != null ? contract.status.eq(status) : null;
    }

    private BooleanExpression contractManagerNameEq(String name) {
        return hasText(name) ? contractEmployee.employee.name.eq(name) : null;
    }

    private BooleanExpression contractSalesMangerNameEq(String name) {
        return hasText(name) ? contractEmployee.employee.name.eq(name) : null;
    }
}

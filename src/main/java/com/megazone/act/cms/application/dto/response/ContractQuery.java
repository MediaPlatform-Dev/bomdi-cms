package com.megazone.act.cms.application.dto.response;

import com.megazone.act.cms.domain.entity.Contract;
import com.megazone.act.cms.domain.entity.ContractEmployee;
import com.megazone.act.cms.domain.type.ContractStatus;
import com.megazone.act.cms.domain.type.ContractType;

import java.time.LocalDate;
import java.util.List;

public record ContractQuery(
    int id,
    String name,
    ContractType contractType,
    ContractStatus status,
    String salesForceContractNo,
    long amount,
    LocalDate contractStartDate,
    LocalDate contractEndDate,
    String customerName,
    String contractManagerName,
    String contractSalesManagerName

) {

    public static ContractQuery from(Contract entity) {
        return new ContractQuery(
            entity.getId(),
            entity.getName(),
            entity.getContractTypes().getContractType(),
            entity.getStatus(),
            entity.getSalesForceContractNo(),
            entity.getContractMoney().getAmount(),
            entity.getContractPeriod().getStartDate(),
            entity.getContractPeriod().getEndDate(),
            entity.getCustomer().getName(),
            contractManagerName(entity.getContractEmployees()),
            contractSalesManager(entity.getContractEmployees())
        );
    }

    // FIXME: 직원 구분값으로 가져오기
    private static String contractManagerName(List<ContractEmployee> employees) {
        if (employees.isEmpty()) {
            return "";
        }
        return employees.get(0).getEmployee().getName();
    }

    private static String contractSalesManager(List<ContractEmployee> employees) {
        if (employees.size() < 2) {
            return "";
        }
        return employees.get(1).getEmployee().getName();
    }
}

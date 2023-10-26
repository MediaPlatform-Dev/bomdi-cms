package com.megazone.act.cms.application.dto.response;

import com.megazone.act.cms.domain.entity.Contract;
import com.megazone.act.cms.domain.type.ContractStatus;
import com.megazone.act.cms.domain.type.ContractType;

import java.time.LocalDate;

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
            entity.getContractEmployees().get(0).getEmployee().getName(),
            entity.getContractEmployees().get(1).getEmployee().getName()
        );
    }
}

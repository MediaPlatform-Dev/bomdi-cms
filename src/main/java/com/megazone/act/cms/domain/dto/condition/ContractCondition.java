package com.megazone.act.cms.domain.dto.condition;

import com.megazone.act.cms.domain.type.*;

import java.time.LocalDate;
import java.util.List;

public record ContractCondition(
    ContractType contractType,
    List<ContractDetailType> detailTypes,
    LocalDate startDate,
    LocalDate endDate,
    LocalDate createdStartDate,
    LocalDate createdEndDate,
    ContractStatus status,
    String contractManagerName,
    String contractSalesManagerName,
    String contractName,
    String customerName
) {

    public boolean hasEmployeeName() {
        return contractManagerName != null || contractSalesManagerName != null;
    }
}

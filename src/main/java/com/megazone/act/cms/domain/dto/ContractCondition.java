package com.megazone.act.cms.domain.dto;

import com.megazone.act.cms.domain.type.*;

import java.time.LocalDate;

public record ContractCondition(
    ContractType contractType,
    ContractDetailType detailType,
    LocalDate startDate,
    LocalDate endDate,
    LocalDate createdStartDate,
    LocalDate createdEndDate,
    ContractStatus status,
    String contractNo
) {
}

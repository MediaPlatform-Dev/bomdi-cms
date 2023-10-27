package com.megazone.act.cms.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EmployeeRoleType implements CodeEnum {
    CONTRACT("CONTRACT", "계약"),
    SALES("SALES", "영업"),
    BUSINESS("BIZ", "사업");

    private final String code;
    private final String description;

    public boolean isContract() {
        return this == CONTRACT;
    }

    public boolean isSales() {
        return this == SALES;
    }
}

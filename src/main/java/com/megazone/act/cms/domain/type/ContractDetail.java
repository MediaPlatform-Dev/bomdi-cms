package com.megazone.act.cms.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
 * 체결 분류 타입
 */
@RequiredArgsConstructor
@Getter
public enum ContractDetail implements CodeEnum {
    CONTRACT("CNTRCT", "계약서"),
    NON_CONTRACT("N-CNTRCT", "기타 비계약서"),
    SALES_ESTIMATE("ESTMT", "매출 견적서"),
    ORDER("ORDER", "발주서");

    private final String code;
    private final String description;
}

package com.megazone.act.cms.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ContractType {
    SALES("SALES", "매출"),
    PURCHASE("PURCHASE", "매입");

    private final String code;
    private final String description;
}

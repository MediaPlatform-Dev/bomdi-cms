package com.megazone.act.cms.domain.type;

import lombok.Getter;

@Getter
public enum ContractType {
    SALES("SALES", "매출"),
    PURCHASE("PURCHASE", "매입");

    private final String code;
    private final String type;

    ContractType(String code, String type) {
        this.code = code;
        this.type = type;
    }
}

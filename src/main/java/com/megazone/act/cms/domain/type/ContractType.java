package com.megazone.act.cms.domain.type;

import lombok.Getter;

@Getter
public enum ContractType1 {
    SALES("매출"), PURCHASE("매입");

    private final String type;

    ContractType1(String type) {
        this.type = type;
    }
}

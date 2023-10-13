package com.megazone.act.cms.domain.type;

import lombok.Getter;

@Getter
public enum ContractType {
    SALES("매출"), PURCHASE("매입");

    private final String type;

    ContractType(String type) {
        this.type = type;
    }
}

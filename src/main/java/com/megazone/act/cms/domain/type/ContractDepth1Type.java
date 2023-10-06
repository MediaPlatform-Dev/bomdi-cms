package com.megazone.act.cms.domain.type;

import lombok.Getter;

@Getter
public enum ContractDepth1Type {
    SALES("매출"), PURCHASE("매입");

    private final String type;

    ContractDepth1Type(String type) {
        this.type = type;
    }
}

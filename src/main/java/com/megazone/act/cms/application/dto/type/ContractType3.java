package com.megazone.act.cms.application.dto.type;

import lombok.Getter;

@Getter
public enum ContractType3 {
    NONE("-");

    private final String type;

    ContractType3(String type) {
        this.type = type;
    }
}

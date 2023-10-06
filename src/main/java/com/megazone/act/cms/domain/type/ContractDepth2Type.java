package com.megazone.act.cms.domain.type;

import lombok.Getter;

@Getter
public enum ContractDepth2Type {
    INFRA("Infra"), NON_INFRA("Non Infra");

    private final String type;

    ContractDepth2Type(String type) {
        this.type = type;
    }
}

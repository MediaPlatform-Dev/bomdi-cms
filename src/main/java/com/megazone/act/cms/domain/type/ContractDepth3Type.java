package com.megazone.act.cms.domain.type;

import lombok.Getter;

@Getter
public enum ContractDepth3Type {
    PS("PS"), MS("MS"), DP("DP"), SI("외주");

    private final String type;

    ContractDepth3Type(String type) {
        this.type = type;
    }
}

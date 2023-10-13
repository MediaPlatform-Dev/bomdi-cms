package com.megazone.act.cms.domain.type;

import lombok.Getter;

@Getter
public enum ContractDetailType {
    AWS("AWS"), PS("PS"), MS("MS"), DP("DP"), OUTSOURCING("외주");

    private final String type;

    ContractDetailType(String type) {
        this.type = type;
    }
}

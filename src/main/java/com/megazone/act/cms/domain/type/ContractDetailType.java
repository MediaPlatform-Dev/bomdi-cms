package com.megazone.act.cms.domain.type;

import lombok.Getter;

@Getter
public enum ContractType2 {
    AWS("AWS"), PS("PS"), MS("MS"), DP("DP"), OUTSOURCING("외주");

    private final String type;

    ContractType2(String type) {
        this.type = type;
    }
}

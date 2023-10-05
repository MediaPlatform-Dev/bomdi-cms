package com.megazone.act.cms.application.dto.type;

import lombok.Getter;

@Getter
public enum ContractType2 {
    INFRA_AWS("Infra AWS"), INFRA_AZURE("Infra Azure");

    private final String type;

    ContractType2(String type) {
        this.type = type;
    }
}

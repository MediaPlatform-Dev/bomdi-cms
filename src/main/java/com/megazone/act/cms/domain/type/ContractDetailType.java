package com.megazone.act.cms.domain.type;

import lombok.Getter;

@Getter
public enum ContractDetailType {
    INFRA("INFRA", "Infra"),
    PROFESSIONAL_SERVICE("PS", "PS"),
    MANAGED_SERVICE("MS", "MS"),
    DIGITAL_PRODUCT("DP", "DP"),
    OUTSOURCING("OUTSOURCE", "외주");

    private final String code;
    private final String description;

    ContractDetailType(String code, String description) {
        this.code = code;
        this.description = description;
    }
}

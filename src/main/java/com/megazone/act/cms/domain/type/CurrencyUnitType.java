package com.megazone.act.cms.domain.type;

import lombok.Getter;

@Getter
public enum CurrencyUnitType {
    KRW("KRW", "KRW"),
    USD("USD", "USD");

    private final String code;

    private final String description;

    CurrencyUnitType(String code, String description) {
        this.code = code;
        this.description = description;
    }
}

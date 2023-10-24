package com.megazone.act.cms.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CurrencyUnitType implements CodeEnum {
    KRW("KRW", "KRW"),
    USD("USD", "USD");

    private final String code;

    private final String description;
}

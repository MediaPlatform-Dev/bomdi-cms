package com.megazone.act.cms.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Getter
public enum ContractDetailType implements CodeEnum {
    INFRA("INFRA", "Infra", false),
    PROFESSIONAL_SERVICE("PS", "PS", false),
    MANAGED_SERVICE("MS", "MS", false),
    DIGITAL_PRODUCT("DP", "DP", false),
    OUTSOURCING("OUTSOURCE", "외주", true);

    private final String code;
    private final String description;
    private final boolean onlyPurchase;

    public static List<ContractDetailType> forSales() {
        return Arrays.stream(ContractDetailType.values())
            .filter(it -> !it.onlyPurchase)
            .toList();
    }

    public static List<ContractDetailType> forPurchase() {
        return Arrays.stream(ContractDetailType.values())
            .filter(it -> it.onlyPurchase)
            .toList();
    }
}

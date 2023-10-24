package com.megazone.act.cms.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum InvoiceType implements CodeEnum {
    TAX("TAX", "세금계산서"),
    CARD_TAX("CARD", "카드 결제 세금 계산서"),
    DEPOSIT("DEPOSIT", "예치금"),
    POA("POA", "POA");

    private final String code;
    private final String description;
}

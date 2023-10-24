package com.megazone.act.cms.domain.type;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 계약 금액 지급 타입
 */
@RequiredArgsConstructor
@Getter
public enum PaymentType implements CodeEnum {
    SUM("SUM", "일괄 지급"),
    MONTHLY("MONTH", "매월 분할 지급"),
    YEARLY("YEAR", "매년 분할 지급");

    private final String code;

    private final String description;
}

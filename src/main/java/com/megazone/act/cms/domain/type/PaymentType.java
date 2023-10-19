package com.megazone.act.cms.domain.type;


import lombok.Getter;

/**
 * 계약 금액 지급 타입
 */
@Getter
public enum PaymentType {
    SUM("SUM", "일괄 지급"),
    MONTHLY("MONTH", "매월 분할 지급"),
    YEARLY("YEAR", "매년 분할 지급");

    private final String code;

    private final String description;

    PaymentType(String code, String description) {
        this.code = code;
        this.description = description;
    }

}

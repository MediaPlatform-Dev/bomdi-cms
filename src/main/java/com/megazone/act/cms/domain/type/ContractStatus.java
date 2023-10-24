package com.megazone.act.cms.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ContractStatus implements CodeEnum {
    SAVED("SAVED", "임시 저장"),
    APPLIED("APPLIED", "등록"),
    APPROVED("APPROVED", "승인"),
    REJECTED("REJECTED", "반려");

    private final String code;
    private final String description;
}

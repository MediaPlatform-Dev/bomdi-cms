package com.megazone.act.cms.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ContractStatus {
    SAVED("저장"), APPLIED("신청"), APPROVED("승인"), REJECTED("반려");

    private final String description;
}

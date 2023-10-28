package com.megazone.act.cms.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ContractStatus implements CodeEnum {
    ALL("ALL", "전체"),
    SAVED("SAVED", "임시 저장"),
    APPLIED("APPLIED", "등록");

    private final String code;
    private final String description;
}

package com.megazone.act.cms.application.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UpdateAction {
    TEMPORARY("임시 저장"),
    SAVED("저장");

    private final String description;
}

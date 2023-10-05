package com.megazone.act.cms.application.dto.type;

import lombok.Getter;

@Getter
public enum DealType {
    BILL("청구서"), TYPE2("미정");

    private final String type;

    DealType(String type) {
        this.type = type;
    }
}

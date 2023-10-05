package com.megazone.act.cms.application.dto.type;

import lombok.Getter;

@Getter
public enum BusinessPartnerType {
    EXISTING("기존"), NONE("-");

    private final String type;

    BusinessPartnerType(String type) {
        this.type = type;
    }
}

package com.megazone.act.cms.application.dto.type;

import lombok.Getter;

@Getter
public enum ClientType {
    NONE("-"), TYPE_1("Type 1"), TYPE_2("Type 2");

    private final String type;

    ClientType(String type) {
        this.type = type;
    }
}

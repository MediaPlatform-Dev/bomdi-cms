package com.megazone.act.cms.domain.type;

import lombok.Getter;

@Getter
public enum ClientType {
    TYPE_1("Type 1"), TYPE_2("Type 2");

    private final String type;

    ClientType(String type) {
        this.type = type;
    }
}

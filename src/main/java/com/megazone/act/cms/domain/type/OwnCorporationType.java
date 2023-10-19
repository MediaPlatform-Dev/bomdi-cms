package com.megazone.act.cms.domain.type;

import lombok.Getter;

@Getter
public enum OwnCorporationType {
    MEGAZONE_CLOUD("MZC", "메가존 클라우드"),
    MEGAZONE_DIGITAL("MZD", "메가존 디지털");

    private final String code;
    private final String description;

    OwnCorporationType(String code, String description) {
        this.code = code;
        this.description = description;
    }
}

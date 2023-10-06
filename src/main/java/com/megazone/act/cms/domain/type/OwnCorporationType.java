package com.megazone.act.cms.domain.type;

import lombok.Getter;

@Getter
public enum OwnCorporationType {
    CLOUD("메가존 클라우드"), DIGITAL("메가존");

    private final String type;

    OwnCorporationType(String type) {
        this.type = type;
    }
}

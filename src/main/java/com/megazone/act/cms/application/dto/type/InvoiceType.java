package com.megazone.act.cms.application.dto.type;

import lombok.Getter;

@Getter
public enum InvoiceType {
    TAXES("세금계산서"), NONE("-");

    private final String type;

    InvoiceType(String type) {
        this.type = type;
    }
}

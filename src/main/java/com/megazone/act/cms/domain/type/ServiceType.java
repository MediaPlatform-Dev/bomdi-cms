package com.megazone.act.cms.domain.type;

import lombok.Getter;

@Getter
public enum ServiceType {
    MSP("MSP"), SW("S/W"), HW("H/W");


    private final String type;

    ServiceType(String type) {
        this.type = type;
    }
}

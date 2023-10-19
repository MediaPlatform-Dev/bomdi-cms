package com.megazone.act.cms.domain.type;

import lombok.Getter;

@Getter
public enum CloudServiceType {
    AWS("AWS","AWS"),
    AZURE("AZURE", "Azure"),
    GOOGLE_CLOUD_PLATFORM("GCP", "GCP"),
    AKAMAI("AKAMAI", "Akamai");

    private final String code;
    private final String description;

    CloudServiceType(String code, String description) {
        this.code = code;
        this.description = description;
    }
}

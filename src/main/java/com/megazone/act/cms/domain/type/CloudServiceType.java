package com.megazone.act.cms.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CloudServiceType implements CodeEnum {
    AWS("AWS","AWS"),
    AZURE("AZURE", "Azure"),
    GOOGLE_CLOUD_PLATFORM("GCP", "GCP"),
    AKAMAI("AKAMAI", "Akamai");

    private final String code;
    private final String description;
}

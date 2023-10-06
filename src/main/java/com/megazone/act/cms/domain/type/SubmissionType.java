package com.megazone.act.cms.domain.type;

import lombok.Getter;

@Getter
public enum SubmissionType {
    SFDC("SFDC"), NONE("-");

    private final String type;

    SubmissionType(String type) {
        this.type = type;
    }
}

package com.megazone.act.cms.domain.type;

import lombok.Getter;

@Getter
public enum SubmissionType {
    SALESFORCE("SFDC", "SFDC"),
    G_MAIL("GMAIL", "G-MAIL");

    private final String code;
    private final String description;

    SubmissionType(String code, String description) {
        this.code = code;
        this.description = description;
    }
}

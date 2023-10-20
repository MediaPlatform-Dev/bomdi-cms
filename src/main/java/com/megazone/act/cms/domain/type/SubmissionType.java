package com.megazone.act.cms.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SubmissionType {
    SALESFORCE("SFDC", "SFDC"),
    G_MAIL("GMAIL", "G-MAIL");

    private final String code;
    private final String description;
}

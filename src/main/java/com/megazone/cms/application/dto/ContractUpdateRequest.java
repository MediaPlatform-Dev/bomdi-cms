package com.megazone.cms.application.dto;

import org.hibernate.validator.constraints.Length;

public record ContractUpdateRequest(
    @Length(min = 3, max = 10)
    String name,
    String contents
) {
}

package com.megazone.bomdi.cms.application.dto;

import org.hibernate.validator.constraints.Length;

public record ContractRequest(
    @Length(min = 3, max = 10)
    String name,
    String contents,
    String contractor

) {
}

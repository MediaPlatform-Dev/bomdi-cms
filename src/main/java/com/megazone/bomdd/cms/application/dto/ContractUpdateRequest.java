package com.megazone.bomdd.cms.application.dto;

import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public record ContractUpdateRequest(
    @NotBlank @Length(min = 3, max = 10)
    String name,

    @NotBlank
    String contents,

    //Optional
    String contractor
) {
}

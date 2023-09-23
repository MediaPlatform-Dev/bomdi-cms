package com.megazone.bomdi.cms.application.dto;

import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public record ContractCreateRequest(
    @NotBlank @Length(min = 3, max = 10)
    String name,

    @NotBlank
    String contents,

    @NotBlank
    String contractor
) {
}

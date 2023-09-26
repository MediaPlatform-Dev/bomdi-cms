package com.megazone.bomdd.cms.application.dto;

import com.megazone.bomdd.cms.domain.Contract;


public record ContractResponse(
    Long id,
    String name,
    String contents,
    String contractor
) {

    public static ContractResponse from(Contract entity) {
        return new ContractResponse(
            entity.getId(),
            entity.getName(),
            entity.getContents(),
            entity.getContractor().getName());
    }
}

package com.megazone.bomdi.cms.application.dto;

import com.megazone.bomdi.cms.domain.Contract;


public record ContractResponse(
    Long id,
    String name,
    String contents,
    String contractor
) {

    public static ContractResponse of(Contract entity) {
        return new ContractResponse(
            entity.getId(),
            entity.getName(),
            entity.getContents(),
            entity.getContractor().getName());
    }
}

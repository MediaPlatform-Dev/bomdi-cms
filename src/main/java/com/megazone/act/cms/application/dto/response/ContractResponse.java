package com.megazone.act.cms.application.dto.response;

import com.megazone.act.cms.domain.Contract;


public record ContractResponse(
    Long id,
    String name,
    String description,
    String contractor
) {

    public static ContractResponse from(Contract entity) {
        return new ContractResponse(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getContractorName());
    }
}

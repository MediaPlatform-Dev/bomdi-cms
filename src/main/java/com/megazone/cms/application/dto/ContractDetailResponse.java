package com.megazone.cms.application.dto;

import com.megazone.cms.domain.Contract;

public record ContractDetailResponse(
    Long id,
    String name,
    String contents,
    String contractor
) {

    public static ContractDetailResponse of(Contract entity) {
        return new ContractDetailResponse(
            entity.getId(),
            entity.getName(),
            entity.getContents(),
            entity.getContractor().getName());
    }
}

package com.megazone.bomdi.cms.application.dto;

import com.megazone.bomdi.cms.domain.Contract;

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

    public static ContractDetailResponse of(Long contractId, ContractUpdateRequest form) {
        return new ContractDetailResponse(
            contractId,
            form.name(),
            form.contents(),
            form.contractor());
    }
}

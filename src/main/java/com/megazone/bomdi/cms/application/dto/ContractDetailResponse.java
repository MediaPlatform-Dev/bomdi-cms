package com.megazone.bomdi.cms.application.dto;

import com.megazone.bomdi.cms.domain.Contract;

import java.util.Optional;

public record ContractDetailResponse(
    Long id,
    String name,
    String contents,
    String contractor,
    String createdBy,
    String modifiedBy
) {

    public static ContractDetailResponse from(Contract entity) {
        return new ContractDetailResponse(
            entity.getId(),
            entity.getName(),
            entity.getContents(),
            entity.getContractor().getName(),
            Optional.ofNullable(entity.getCreatedBy()).orElse(""),
            Optional.ofNullable(entity.getModifiedBy()).orElse("")
        );
    }

    public static ContractDetailResponse of(Long contractId, ContractUpdateRequest form) {
        return new ContractDetailResponse(
            contractId,
            form.name(),
            form.contents(),
            form.contractor(),
            "",
            ""
        );
    }
}

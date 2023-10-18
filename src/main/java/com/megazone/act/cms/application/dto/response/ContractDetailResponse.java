package com.megazone.act.cms.application.dto.response;

import com.megazone.act.cms.domain.ContractDetail;
import com.megazone.act.cms.domain.type.ContractDetailType;

public record ContractDetailResponse(
    Long id,
    String name,
    ContractDetailType type
) {

    public static ContractDetailResponse from(ContractDetail entity) {
        return new ContractDetailResponse(
            entity.getId(),
            entity.getName(),
            entity.getContractDetailType()
        );
    }
}

package com.megazone.act.cms.application.dto.response;

import com.megazone.act.cms.domain.entity.ContractDetail;
import com.megazone.act.cms.domain.type.ContractDetailType;

public record ContractResponse(
    Long id,
    String name,
    ContractDetailType type
) {

    public static ContractResponse from(ContractDetail entity) {
        return new ContractResponse(
            entity.getId(),
            entity.getName(),
            entity.getContractDetailType()
        );
    }
}

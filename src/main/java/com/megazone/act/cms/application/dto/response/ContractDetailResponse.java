package com.megazone.act.cms.application.dto.response;

import com.megazone.act.cms.domain.entity.ContractDetail;
import com.megazone.act.cms.domain.type.ContractDetailType;
import com.megazone.act.cms.domain.type.ContractType;

public record ContractDetailResponse(
    int id,
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

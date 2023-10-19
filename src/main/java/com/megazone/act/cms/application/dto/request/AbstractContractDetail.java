package com.megazone.act.cms.application.dto.request;

import com.megazone.act.cms.domain.type.ContractDetailType;
import com.megazone.act.cms.domain.type.CloudServiceType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class AbstractContractDetail {
    private ContractDetailType type;
    private CloudServiceType cloudServiceType;
    private String name;

    AbstractContractDetail(ContractDetailType type) {
        this.type = type;
    }
}

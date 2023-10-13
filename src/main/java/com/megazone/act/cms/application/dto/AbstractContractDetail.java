package com.megazone.act.cms.application.dto;

import com.megazone.act.cms.domain.type.ContractDetailType;
import com.megazone.act.cms.domain.type.ServiceType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class AbstractContractDetail {
    private ContractDetailType type;
    private ServiceType serviceType;
    private String name;

    public AbstractContractDetail(ContractDetailType type) {
        this.type = type;
    }
}

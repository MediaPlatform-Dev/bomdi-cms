package com.megazone.act.cms.application.dto;

import com.megazone.act.cms.domain.type.*;
import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ContractCreateTypes {
    public static final ContractCreateTypes DEFAULT = new ContractCreateTypes(ContractDepth1Type.SALES, ContractDepth2Type.INFRA, ContractDepth3Type.PS);

    private ContractDepth1Type contractDepth1Type;
    private ContractDepth2Type contractDepth2Type;
    private ContractDepth3Type contractDepth3Type;

    public boolean isSales() {
        return contractDepth1Type == ContractDepth1Type.SALES;
    }
}

package com.megazone.act.cms.application.dto;

import com.megazone.act.cms.domain.type.*;
import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ContractCreateTypes {
    public static final ContractCreateTypes DEFAULT = new ContractCreateTypes(ContractType.SALES, ContractDetailType.PS);

    private ContractType contractType;
    private ContractDetailType contractDetailType;

    public boolean isSales() {
        return contractType == ContractType.SALES;
    }
}

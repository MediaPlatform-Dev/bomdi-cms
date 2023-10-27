package com.megazone.act.cms.domain.dto.query;

import com.megazone.act.cms.domain.type.ContractDetailType;
import lombok.Getter;

@Getter
public class ContractDetailQuery {
    int contractId;
    ContractDetailType type;
}

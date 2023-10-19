package com.megazone.act.cms.application.dto.request;

import com.megazone.act.cms.domain.type.ContractType;
import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class ContractUpdateRequest {

    protected ContractType contractType;
    @Setter
    protected ContractAwsDetail awsDetail;
    @Setter
    protected ContractPsDetail psDetail;
    @Setter
    protected ContractMsDetail msDetail;
    @Setter
    protected ContractDpDetail dpDetail;
}

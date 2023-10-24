package com.megazone.act.cms.domain.entity.convertor;

import com.megazone.act.cms.domain.type.ContractStatus;

public class ContractStatusConvertor extends CodeEnumConvertor<ContractStatus> {

    @Override
    Class<ContractStatus> supprotClass() {
        return ContractStatus.class;
    }

    @Override
    ContractStatus defaultType() {
        return ContractStatus.SAVED;
    }
}

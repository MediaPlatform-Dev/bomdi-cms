package com.megazone.act.cms.domain.entity.convertor;

import com.megazone.act.cms.domain.type.ContractDetailType;

public class ContractDetailTypeConvertor extends CodeEnumConvertor<ContractDetailType> {

    @Override
    Class<ContractDetailType> supprotClass() {
        return ContractDetailType.class;
    }

    @Override
    ContractDetailType defaultType() {
        return ContractDetailType.INFRA;
    }
}

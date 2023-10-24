package com.megazone.act.cms.domain.entity.convertor;

import com.megazone.act.cms.domain.type.ContractType;

public class ContractTypeConvertor extends CodeEnumConvertor<ContractType> {

    @Override
    Class<ContractType> supprotClass() {
        return ContractType.class;
    }
}

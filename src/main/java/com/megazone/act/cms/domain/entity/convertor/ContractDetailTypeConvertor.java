package com.megazone.act.cms.domain.entity.convertor;

import com.megazone.act.cms.domain.type.ContractDetail;

public class ContractDetailTypeConvertor extends CodeEnumConvertor<ContractDetail> {

    @Override
    Class<ContractDetail> supprotClass() {
        return ContractDetail.class;
    }
}

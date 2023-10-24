package com.megazone.act.cms.domain.entity.convertor;

import com.megazone.act.cms.domain.type.DealType;

public class DealTypeConvertor extends CodeEnumConvertor<DealType> {

    @Override
    Class<DealType> supprotClass() {
        return DealType.class;
    }

    @Override
    DealType defaultType() {
        return DealType.CONTRACT;
    }
}

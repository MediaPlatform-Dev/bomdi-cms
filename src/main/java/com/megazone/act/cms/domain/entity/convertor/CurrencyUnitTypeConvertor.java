package com.megazone.act.cms.domain.entity.convertor;

import com.megazone.act.cms.domain.type.CurrencyUnitType;

public class CurrencyUnitTypeConvertor extends CodeEnumConvertor<CurrencyUnitType> {

    @Override
    Class<CurrencyUnitType> supprotClass() {
        return CurrencyUnitType.class;
    }

    @Override
    CurrencyUnitType defaultType() {
        return CurrencyUnitType.KRW;
    }
}

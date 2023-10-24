package com.megazone.act.cms.domain.entity.convertor;

import com.megazone.act.cms.domain.type.InvoiceType;

public class InvoiceTypeConvertor extends CodeEnumConvertor<InvoiceType> {

    @Override
    Class<InvoiceType> supprotClass() {
        return InvoiceType.class;
    }

    @Override
    InvoiceType defaultType() {
        return InvoiceType.TAX;
    }
}

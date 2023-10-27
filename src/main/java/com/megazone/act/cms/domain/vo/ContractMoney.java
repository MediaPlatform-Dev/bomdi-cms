package com.megazone.act.cms.domain.vo;

import jakarta.persistence.*;

import lombok.*;

import com.megazone.act.cms.domain.entity.convertor.CurrencyUnitTypeConvertor;
import com.megazone.act.cms.domain.type.CurrencyUnitType;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class ContractMoney {

    @Convert(converter = CurrencyUnitTypeConvertor.class)
    private CurrencyUnitType currencyUnitType;

    @Column(name = "amt")
    private long amount;

    private String remark;

    public ContractMoney(CurrencyUnitType currencyUnitType, long amount, String remark) {
        this.currencyUnitType = currencyUnitType;
        this.amount = amount;
        this.remark = remark;
    }

}

package com.megazone.act.cms.domain.vo;

import com.megazone.act.cms.domain.entity.convertor.CurrencyUnitTypeConvertor;
import com.megazone.act.cms.domain.type.CurrencyUnitType;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class ContractMoney {

    @Convert(converter = CurrencyUnitTypeConvertor.class)
    private CurrencyUnitType currencyUnitType;

    @Column(name = "amt")
    private long amount;

    private boolean hasVat;

    @Column(name = "tot_amt")
    private long totalAmount;


    public ContractMoney(CurrencyUnitType currencyUnitType, long amount, boolean hasVat) {
        this.currencyUnitType = currencyUnitType;
        this.amount = amount;
        this.hasVat = hasVat;
        this.totalAmount = total(amount, hasVat);
    }

    private long total(long amount, boolean hasVat) {
        if (!hasVat) {
            return amount + calculateVat(amount);
        }
        return amount;
    }

    private static long calculateVat(long amount) {
        return (long) (amount * 0.1);
    }
}

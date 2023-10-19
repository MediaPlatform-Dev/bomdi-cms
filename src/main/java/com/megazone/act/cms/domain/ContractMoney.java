package com.megazone.act.cms.domain;

import com.megazone.act.cms.domain.type.CurrencyUnitType;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class ContractMoney {

    @Enumerated(value = EnumType.STRING)
    private CurrencyUnitType currencyUnitType;

    @Column(name = "amt")
    private int amount;

    @Column(name = "tot_amt")
    private int totalAmount;

    public ContractMoney(CurrencyUnitType currencyUnitType, int totalAmount) {
        this.currencyUnitType = currencyUnitType;
        this.totalAmount = totalAmount;
        this.amount = totalAmount;
    }
}

package com.megazone.act.cms.domain;

import com.megazone.act.cms.domain.type.CurrencyUnit;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class ContractMoney {

    @Enumerated(value = EnumType.STRING)
    private CurrencyUnit currencyUnit;

    @Column(name = "amt")
    private int amount;

    @Column(name = "tot_amt")
    private int totalAmount;

    public ContractMoney(CurrencyUnit currencyUnit, int totalAmount) {
        this.currencyUnit = currencyUnit;
        this.totalAmount = totalAmount;
        this.amount = totalAmount;
    }
}

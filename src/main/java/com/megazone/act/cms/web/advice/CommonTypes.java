package com.megazone.act.cms.web.advice;

import com.megazone.act.cms.domain.type.*;
import lombok.Getter;

@Getter
public class CommonTypes {
    public static final CommonTypes TYPES = new CommonTypes();

    private final ContractType[] contractTypes = ContractType.values();
    private final ContractDetailType[] contractDetailTypes = ContractDetailType.values();
    private final ContractStatus[] contractStatuses = ContractStatus.values();
    private final CurrencyUnitType[] currencyUnitTypes = CurrencyUnitType.values();
    private final DealType[] dealTypes = DealType.values();
    private final InvoiceType[] invoiceTypes = InvoiceType.values();
    private final SubmissionType[] submissionTypes = SubmissionType.values();
}

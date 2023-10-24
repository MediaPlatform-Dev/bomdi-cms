package com.megazone.act.cms.application.dto.response;

import com.megazone.act.cms.application.dto.request.CustomerInfo;
import com.megazone.act.cms.domain.entity.Contract;
import com.megazone.act.cms.domain.type.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


public record ContractResponse(
    Long id,
    ContractType type,
    String no,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate contractDate,
    String name,
    String salesForceContractNo,
    String beforeRenewalContractNo, // TODO: 네이밍 수정 예정
    boolean isApproved,
    boolean isUpdated,
    SubmissionType submissionType,
    String corporationName,
    String serviceType,
    DealType dealType,
    String businessPartnerName,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate contractStartDate,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate contractEndDate,
    String contractorName,
    String salesPersonName,
    InvoiceType invoiceType,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate taxesCreatedDate,
    String taxesReceivedEmail,
    String description,
    CurrencyUnitType currencyUnitType,
    long amount,
    List<CustomerInfo> customerInfos,
    List<ContractDetailResponse> contractDetails

) {

    public static ContractResponse from(Contract entity) {
        return new ContractResponse(
            entity.getId(),
            entity.getContractTypes().getContractType(),
            entity.getNo(),
            LocalDate.now(),
            entity.getName(),
            entity.getSalesForceContractNo()
            ,"",
            false,
            false,
            entity.getContractTypes().getSubmissionType(),
            "",
            "",
            entity.getContractTypes().getDealType(),
            "",
            entity.getPeriod().getStartDate(),
            entity.getPeriod().getEndDate(),
            "",
            "",
            InvoiceType.TAX,
            LocalDate.now(),
            "",
            entity.getRemark(),
            entity.getContractMoney().getCurrencyUnitType(),
            entity.getContractMoney().getTotalAmount(),
            List.of(),
            entity.getContractDetails()
                .stream()
                .map(ContractDetailResponse::from)
                .toList()
        );
    }

    public List<String> getContractDetailTypes() {
        return contractDetails.stream().map(it -> it.type().getDescription()).toList();
    }
}

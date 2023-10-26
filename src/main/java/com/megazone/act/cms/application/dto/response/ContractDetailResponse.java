package com.megazone.act.cms.application.dto.response;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.megazone.act.cms.application.dto.request.ContractCustomerEmployeeResponse;
import com.megazone.act.cms.domain.entity.Contract;
import com.megazone.act.cms.domain.type.*;


public record ContractDetailResponse(
    Long id,
    ContractType type,
    String no,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate contractDate,
    String name,
    String salesForceContractNo,
    // TODO: 네이밍 수정 예정
    boolean isApproved,
    boolean isUpdated,
    SubmissionType submissionType,
    String corporationName,
    String serviceType,
    DealType dealType,
    String customerName,

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate contractStartDate,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate contractEndDate,

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate invoiceStartDate,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate invoiceEndDate,

    String contractManagerName,

    String contractSalesManagerName,

    String EdmLinkUrl,
    InvoiceType invoiceType,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate taxesCreatedDate,
    String taxesReceivedEmail,

    String amountRemark,

    String remark,
    CurrencyUnitType currencyUnitType,
    long amount,
    List<ContractCustomerEmployeeResponse> customerEmployees,
    List<ContractResponse> contractDetails

) {

    public static ContractDetailResponse from(Contract entity) {

        return new ContractDetailResponse(
            entity.getId(),
            entity.getContractTypes().getContractType(),
            entity.getNo(),
            LocalDate.now(),
            entity.getName(),
            entity.getSalesForceContractNo(),
            false,
            false,
            entity.getContractTypes().getSubmissionType(),
            entity.getCorporation().getName(),
            "",
            entity.getContractTypes().getDealType(),
            entity.getCustomer().getName(),
            entity.getContractPeriod().getStartDate(),
            entity.getContractPeriod().getEndDate(),
            entity.getInvoicePeriod().getStartDate(),
            entity.getInvoicePeriod().getEndDate(),
            entity.getContractEmployees().get(0).getEmployee().getName(), // FIXME: 직원 구분값으로 가져오기
            entity.getContractEmployees().get(1).getEmployee().getName(), // FIXME: 직원 구분값으로 가져오기
            entity.getEdmLinkUrl(),
            entity.getContractTypes().getInvoiceType(),
            LocalDate.now(),
            "",
            entity.getContractMoney().getRemark(),
            entity.getRemark(),
            entity.getContractMoney().getCurrencyUnitType(),
            entity.getContractMoney().getTotalAmount(),
            entity.getContractCustomerEmployees()
                .stream()
                .map(ContractCustomerEmployeeResponse::from)
                .toList(),
            entity.getContractDetails()
                .stream()
                .map(ContractResponse::from)
                .toList()
        );
    }

    public List<String> getContractDetailTypes() {
        return contractDetails.stream().map(it -> it.type().getCode()).toList();
    }
}

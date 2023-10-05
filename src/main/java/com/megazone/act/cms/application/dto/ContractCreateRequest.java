package com.megazone.act.cms.application.dto;

import com.megazone.act.cms.application.dto.type.*;
import jakarta.validation.constraints.NotBlank;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.*;

@Getter
@EqualsAndHashCode
@ToString
public final class ContractCreateRequest {

    private final ContractType contractType;
    private final ContractType2 contractType2;
    private final ContractType3 contractType3;
    private final Boolean isSalesApproved;
    private final Boolean isSalesUpdated;
    private final String salesContractId;
    private final String beforeUpdateSalesId;
    private final @NotBlank @Length(min = 3, max = 10) String name;
    private final String salesForceContractId;
    private final SubmissionType submissionType;
    private final OwnCorporationType ownCorporationType;
    private final ServiceType serviceType;
    private final DealType dealType;
    private final String businessPartnerName;
    private final BusinessPartnerType businessPartnerType;
    private final CurrencyUnit currencyUnit;
    private final Long totalContractAmount;
    private final LocalDate contractStartDate;
    private final LocalDate contractEndDate;
    private final String contractor;
    private final String salesPersonName;
    private final String edmLinkURL;
    private final InvoiceType invoiceType;
    private final LocalDate taxesCreatedDate;
    private final String taxesReceivedMail;
    private final String contents;
    @Setter
    private List<ClientInfo> clientInfos;
    private final MultipartFile contractFile;
    private final MultipartFile businessRegistrationFile;
    private final MultipartFile etcFile;

    public ContractCreateRequest(
        // 계약 유형
        ContractType contractType,
        ContractType2 contractType2,
        ContractType3 contractType3,

        // 계약 기본 정보 1
        Boolean isSalesApproved,
        Boolean isSalesUpdated,
        String salesContractId,
        String beforeUpdateSalesId,
        @NotBlank @Length(min = 3, max = 10)
        String name,
        String salesForceContractId,

        // 계약 기본 정보2
        SubmissionType submissionType,
        OwnCorporationType ownCorporationType,
        ServiceType serviceType,
        DealType dealType,
        String businessPartnerName,
        BusinessPartnerType businessPartnerType,
        CurrencyUnit currencyUnit,
        Long totalContractAmount,
        LocalDate contractStartDate,
        LocalDate contractEndDate,
        String contractor,
        String salesPersonName,
        String edmLinkURL,
        InvoiceType invoiceType,
        LocalDate taxesCreatedDate,
        String taxesReceivedMail,
        String contents,

        // 고객사 정보
        List<ClientInfo> clientInfos,

        // 첨부 파일
        MultipartFile contractFile,
        MultipartFile businessRegistrationFile,
        MultipartFile etcFile

        // 매출 계약 매핑 매입 계약 정보
    ) {
        this.contractType = contractType;
        this.contractType2 = contractType2;
        this.contractType3 = contractType3;
        this.isSalesApproved = isSalesApproved;
        this.isSalesUpdated = isSalesUpdated;
        this.salesContractId = salesContractId;
        this.beforeUpdateSalesId = beforeUpdateSalesId;
        this.name = name;
        this.salesForceContractId = salesForceContractId;
        this.submissionType = submissionType;
        this.ownCorporationType = ownCorporationType;
        this.serviceType = serviceType;
        this.dealType = dealType;
        this.businessPartnerName = businessPartnerName;
        this.businessPartnerType = businessPartnerType;
        this.currencyUnit = currencyUnit;
        this.totalContractAmount = totalContractAmount;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.contractor = contractor;
        this.salesPersonName = salesPersonName;
        this.edmLinkURL = edmLinkURL;
        this.invoiceType = invoiceType;
        this.taxesCreatedDate = taxesCreatedDate;
        this.taxesReceivedMail = taxesReceivedMail;
        this.contents = contents;
        this.clientInfos = clientInfos;
        this.contractFile = contractFile;
        this.businessRegistrationFile = businessRegistrationFile;
        this.etcFile = etcFile;
    }

    public static ContractCreateRequest empty() {
        return new ContractCreateRequest(
            ContractType.SALES,
            ContractType2.INFRA_AWS,
            ContractType3.NONE,
            false,
            false,
            null,
            null,
            null,
            null,
            SubmissionType.NONE,
            OwnCorporationType.CLOUD,
            ServiceType.SW,
            DealType.BILL,
            null,
            BusinessPartnerType.NONE,
            CurrencyUnit.KRW,
            0L,
            null,
            null,
            null,
            null,
            null,
            InvoiceType.TAXES,
            null,
            null,
            null,
            List.of(new ClientInfo(ClientType.NONE, "", "", "")),
            null,
            null,
            null
        );
    }
}

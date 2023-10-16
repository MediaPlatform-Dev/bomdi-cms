package com.megazone.act.cms.application.dto;

import com.megazone.act.cms.domain.type.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Getter
@EqualsAndHashCode
@ToString
public final class ContractCreateRequest {
    // 계약 유형
    private final ContractType contractType;
    @Setter
    private ContractAwsDetail awsDetail;
    @Setter
    private ContractPsDetail psDetail;
    @Setter
    private ContractMsDetail msDetail;
    @Setter
    private ContractDpDetail dpDetail;
    @Setter
    private ContractOutSourcingDetail outSourcingDetail;

    private final Boolean isApproved;
    private final Boolean isUpdated;
    private final String contractId;
    private final String beforeUpdateId;
    @NotBlank @Length(min = 3, max = 10)
    private final String name;
    private final String salesForceContractId;
    private final SubmissionType submissionType;
    private final OwnCorporationType ownCorporationType;
    private final ServiceType serviceType;
    private final DealType dealType;
    private final String businessPartnerName;
    private final BusinessPartnerType businessPartnerType;
    private final CurrencyUnit currencyUnit;
    private final Integer totalContractAmount;
    private final LocalDate contractStartDate;
    private final LocalDate contractEndDate;
    private final String contractor;
    private final String salesPersonName;
    private final String edmLinkURL;
    private final InvoiceType invoiceType;
    private final LocalDate taxesCreatedDate;
    private final String taxesReceivedMail;
    private final String description;
    @Setter
    private List<ClientInfo> clientInfos;
    private final MultipartFile contractFile;
    private final MultipartFile businessRegistrationFile;
    private final MultipartFile etcFile;

    public ContractCreateRequest(
        // 계약 유형
        ContractType contractType,
        ContractAwsDetail awsDetail,
        ContractPsDetail psDetail,
        ContractMsDetail msDetail,
        ContractDpDetail dpDetail,

        // 계약 기본 정보 1
        Boolean isApproved,
        Boolean isUpdated,
        String contractId,
        String beforeUpdateId,
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
        Integer totalContractAmount,
        LocalDate contractStartDate,
        LocalDate contractEndDate,
        String contractor,
        String salesPersonName,
        String edmLinkURL,
        InvoiceType invoiceType,
        LocalDate taxesCreatedDate,
        String taxesReceivedMail,
        String description,

        // 고객사 정보
        List<ClientInfo> clientInfos,

        // 첨부 파일
        MultipartFile contractFile,
        MultipartFile businessRegistrationFile,
        MultipartFile etcFile

        // 매출 계약 매핑 매입 계약 정보
    ) {
        this.contractType = contractType;
        this.awsDetail = awsDetail;
        this.psDetail = psDetail;
        this.msDetail = msDetail;
        this.dpDetail = dpDetail;
        this.isApproved = isApproved;
        this.isUpdated = isUpdated;
        this.contractId = contractId;
        this.beforeUpdateId = beforeUpdateId;
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
        this.description = description;
        this.clientInfos = clientInfos;
        this.contractFile = contractFile;
        this.businessRegistrationFile = businessRegistrationFile;
        this.etcFile = etcFile;
    }

    public static ContractCreateRequest empty() {
        return from(ContractCreateTypes.DEFAULT);
    }

    public static ContractCreateRequest from(ContractCreateTypes contractCreateTypes) {
        return new ContractCreateRequest(
            contractCreateTypes.getContractType(),
            //new ContractAwsDetail(),
            //new ContractPsDetail(),
            //new ContractMsDetail(),
            //new ContractDpDetail(),
            null,
            null,
            null,
            null,
            false,
            false,
            null,
            null,
            null,
            null,
            SubmissionType.SFDC,
            OwnCorporationType.CLOUD,
            ServiceType.SW,
            DealType.BILL,
            null,
            BusinessPartnerType.EXISTING,
            CurrencyUnit.KRW,
            0,
            null,
            null,
            null,
            null,
            null,
            InvoiceType.TAXES,
            null,
            null,
            null,
            List.of(ClientInfo.empty()),
            null,
            null,
            null
        );
    }

    public List<AbstractContractDetail> getContractDetails() {
        return Stream.of(awsDetail, psDetail, msDetail, dpDetail, outSourcingDetail)
            .filter(Objects::nonNull)
            .toList();
    }
}

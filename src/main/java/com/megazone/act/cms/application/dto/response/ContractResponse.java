package com.megazone.act.cms.application.dto.response;

import com.megazone.act.cms.domain.Contract;
import com.megazone.act.cms.domain.type.ClientInfo;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


public record ContractResponse(
    Long id,
    String number,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate contractDate,
    String name,
    String salesForceContractNumber,
    boolean isApproved,
    boolean isUpdated,
    String submissionType,
    String corporationName,
    String serviceType,
    String dealType,
    String businessPartnerName,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate contractStartDate,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate contractEndDate,
    String contractorName,
    String salesPersonName,
    String invoiceType,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate taxesCreatedDate,
    String taxesReceivedEmail,
    String description,
    int amount,
    List<ClientInfo> clients,
    List<ContractDetailResponse> contractDetails

) {

    public static ContractResponse from(Contract entity) {
        return new ContractResponse(
            entity.getId(),
            entity.getNumber(),
            LocalDate.now(),
            entity.getName(),
            entity.getSalesForceContractId(),
            false,
            false,
            entity.getContractTypes().getSubmissionType().getType(),
            entity.getCorporation().getName(),
            "",
            entity.getContractTypes().getDealType().getType(),
            entity.getBusinessPartner().getName(),
            entity.getPeriod().getStartDate(),
            entity.getPeriod().getEndDate(),
            entity.getContractorName(),
            entity.getSalesPersonName(),
            "",
            LocalDate.now(),
            "",
            entity.getDescription(),
            entity.getContractMoney().getAmount(),
            List.of(),
            entity.getContractDetails()
                .stream()
                .map(ContractDetailResponse::from)
                .toList()
        );
    }

    public List<String> getContractDetailTypes() {
        return contractDetails.stream().map(it -> it.type().getType()).toList();
    }
}

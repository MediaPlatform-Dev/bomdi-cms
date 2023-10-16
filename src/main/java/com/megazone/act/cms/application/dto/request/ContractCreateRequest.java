package com.megazone.act.cms.application.dto.request;

import com.megazone.act.cms.domain.type.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public final class ContractCreateRequest {
    public static final ContractCreateRequest EMPTY;

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

    private final String contractId;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "계약 시작일을 선택해 주세요.")
    private final LocalDate contractStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "계약 종료일을 선택해 주세요.")
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

    static {
        EMPTY = new ContractCreateRequest(
            ContractType.SALES,
            null,
            null,
            null,
            null,
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
            List.of(ClientInfo.EMPTY),
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

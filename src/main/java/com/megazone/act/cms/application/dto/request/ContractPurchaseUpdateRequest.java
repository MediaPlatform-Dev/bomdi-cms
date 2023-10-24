package com.megazone.act.cms.application.dto.request;

import com.megazone.act.cms.domain.type.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ContractPurchaseUpdateRequest {

    private final ContractType contractType = ContractType.PURCHASE;
    @Setter
    private ContractAwsDetail awsDetail;
    @Setter
    private ContractPsDetail psDetail;
    @Setter
    private ContractMsDetail msDetail;
    @Setter
    private ContractDpDetail dpDetail;

    // 계약 기본 정보1
    private LocalDate contractDate;
    private String name;
    private boolean isRenewal;
    private String salesForceContractNo;
    private String beforeRenewalContractNo; // TODO: 네이밍 수정 예정


    // 계약 기본 정보2
    private SubmissionType submissionType = SubmissionType.SALESFORCE;
    private OwnCorporationType ownCorporationType = OwnCorporationType.MEGAZONE_DIGITAL;
    private CloudServiceType cloudServiceType = CloudServiceType.AZURE;
    private ContractDetail dealType = ContractDetail.CONTRACT;

    private String customerName; // TODO: 고객사 테이블 생성 시 ID로 변경이 필요함

    // 계약 금액
    private PaymentType paymentType = PaymentType.YEARLY;
    private long amount;
    private CurrencyUnitType currencyUnitType = CurrencyUnitType.KRW;
    private long vat;
    private long advancePaymentAmount;
    private PaymentType advancePaymentType = PaymentType.YEARLY;
    private CurrencyUnitType advancePaymentCurrencyUnitType = CurrencyUnitType.KRW;
    private String contractAmountRemark;

    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private LocalDate invoiceStartDate;
    private LocalDate invoiceEndDate;

    // TODO: 지불 조건 필드
    private String contractManagerName; // TODO: 자사 담당자 테이블 생성 시 ID로 변경이 필요함
    private String salesManagerName; // TODO: 자사 담당자 테이블 생성 시 ID로 변경이 필요함
    // TODO: EDM Link URL 무엇인지..
    private InvoiceType invoiceType = InvoiceType.TAX;
    // TODO: 세금계산서 작성일 기타 부분이 애매함
    @Email
    private String taxReceivedEmail;

    // TODO: AWS 통합 빌링(신규) 위치 애매함
    private String remark;

    // 고객사 담당자
    private List<CustomerInfo> customerInfos;

    // 증빙 서류
    private MultipartFile contractFile;
    private MultipartFile businessRegistrationFile;
    private MultipartFile secretContractFile;
    // TODO: 첨부 문서 리스트 여러개인지..

    private String documentRemark;
}

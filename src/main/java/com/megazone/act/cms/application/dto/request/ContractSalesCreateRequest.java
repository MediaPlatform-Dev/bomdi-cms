package com.megazone.act.cms.application.dto.request;

import com.megazone.act.cms.domain.*;
import com.megazone.act.cms.domain.entity.*;
import com.megazone.act.cms.domain.type.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContractSalesCreateRequest {

    public static final ContractSalesCreateRequest EMPTY = new ContractSalesCreateRequest();

    private final ContractType contractType = ContractType.SALES;
    private UpdateAction action;
    private ContractAwsDetail awsDetail;
    private ContractPsDetail psDetail;
    private ContractMsDetail msDetail;
    private ContractDpDetail dpDetail;

    // 계약 기본 정보1
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractDate;
    private String name;
    private boolean isRenewal;
    private String salesForceContractNo;
    private String beforeRenewalContractNo; // TODO: 네이밍 수정 예정


    // 계약 기본 정보2
    private SubmissionType submissionType = SubmissionType.SALESFORCE;
    private OwnCorporationType ownCorporationType = OwnCorporationType.MEGAZONE_DIGITAL;
    private CloudServiceType cloudServiceType = CloudServiceType.AZURE;
    private DealType dealType = DealType.CONTRACT;

    private int customerId;

    // 계약 금액
    private PaymentType paymentType = PaymentType.SUM;
    private long amount;
    private CurrencyUnitType currencyUnitType = CurrencyUnitType.KRW;
    private long vat;
    private long advancePaymentAmount;
    private PaymentType advancePaymentType = PaymentType.SUM;
    private CurrencyUnitType advancePaymentCurrencyUnitType = CurrencyUnitType.KRW;
    private String contractAmountRemark;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractEndDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate invoiceStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate invoiceEndDate;

    // TODO: 지불 조건 필드
    private int contractManagerId;
    private int salesManagerId;
    // TODO: EDM Link URL 무엇인지..
    private InvoiceType invoiceType = InvoiceType.TAX;
    // TODO: 세금계산서 작성일 기타 부분이 애매함
    private String taxReceivedEmail;

    // TODO: AWS 통합 빌링(신규) 위치 애매함
    private String remark;

    // 고객사 담당자
    private List<Integer> customerEmployeeIds = new ArrayList<>();

    // 증빙 서류
    private MultipartFile contractFile;
    private MultipartFile businessRegistrationFile;
    private MultipartFile secretContractFile;
    // TODO: 첨부 문서 리스트 여러개인지..

    private String documentRemark;

    public Contract toEntity(
        Corporation corporation,
        List<Employee> employees,
        Customer customer,
        List<CustomerEmployee> customerEmployees
    ) {

        return new Contract(name, remark, salesForceContractNo,
            corporation, new ContractTypes(contractType, dealType, submissionType),
            new ContractPeriod(contractStartDate, contractEndDate),
            new ContractMoney(currencyUnitType, amount),
            employees.stream().map(ContractEmployee::new).toList(),
            customer,
            customerEmployees.stream().map(ContractCustomerEmployee::new).toList(),
            getContractDetails()
        );
    }

    private List<ContractDetail> getContractDetails() {
        return Stream.of(awsDetail, psDetail, msDetail, dpDetail)
            .filter(Objects::nonNull)
            .map(it -> new ContractDetail(it.getName(), it.getType()))
            .toList();
    }
}


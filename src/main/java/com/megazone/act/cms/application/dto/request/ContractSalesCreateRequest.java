package com.megazone.act.cms.application.dto.request;

import com.megazone.act.cms.domain.entity.*;
import com.megazone.act.cms.domain.type.*;
import com.megazone.act.cms.domain.type.DealType;
import com.megazone.act.cms.domain.vo.*;
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

    private static final ContractType contractType = ContractType.SALES;
    private UpdateAction action;
    private ContractInfraDetail infraDetail;
    private ContractPsDetail psDetail;
    private ContractMsDetail msDetail;
    private ContractDpDetail dpDetail;

    // 계약 기본 정보1
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractDate;
    private String name;
    private String salesForceContractNo;
    // TODO: 계약 관계 ID

    // 계약 기본 정보2
    private SubmissionType submissionType = SubmissionType.SALESFORCE;
    private CloudServiceType cloudServiceType = CloudServiceType.AZURE;
    private DealType dealType = DealType.CONTRACT;
    private int customerId;

    private long amount;
    private CurrencyUnitType currencyUnitType = CurrencyUnitType.KRW;
    private boolean hasVat;
    private String contractAmountRemark;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractEndDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate invoiceStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate invoiceEndDate;

    // TODO: 통합 빌링 위치 애매함
    private InvoiceType invoiceType = InvoiceType.TAX;
    // TODO: 세금계산서 작성일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate taxCreatedDate;
    private String taxReceivedEmail;

    private int contractManagerId;
    private int salesManagerId;
    // TODO: EDM Link URL 무엇인지
    private String edmLinkUrl;
    private String remark;

    private List<Integer> customerEmployeeIds = new ArrayList<>();

    // TODO: 사업 수행 담당자 Id
    private List<Integer> pmIds = new ArrayList<>();

    // 증빙 서류
    private MultipartFile contractFile;
    private MultipartFile businessRegistrationFile;
    private MultipartFile secretContractFile;
    // TODO: 파일 변수 네이밍 정의
    private MultipartFile file1;
    private MultipartFile file2;
    private MultipartFile file3;
    private String documentRemark;

    // TODO: 추가 사항

    public Contract toEntity(
        Corporation corporation,
        List<Employee> employees,
        Customer customer,
        List<CustomerEmployee> customerEmployees
    ) {
        return new Contract(name, remark, salesForceContractNo,
            corporation, new ContractTypes(contractType, dealType, submissionType, invoiceType),
            new ContractPeriod(contractStartDate, contractEndDate),
            new ContractMoney(currencyUnitType, amount, hasVat),
            employees.stream().map(ContractEmployee::new).toList(),
            customer,
            customerEmployees.stream().map(ContractCustomerEmployee::new).toList(),
            getContractDetails()
        );
    }

    private List<com.megazone.act.cms.domain.entity.ContractDetail> getContractDetails() {
        return Stream.of(infraDetail, psDetail, msDetail, dpDetail)
            .filter(Objects::nonNull)
            .map(it -> new com.megazone.act.cms.domain.entity.ContractDetail(it.getName(), it.getType()))
            .toList();
    }
}


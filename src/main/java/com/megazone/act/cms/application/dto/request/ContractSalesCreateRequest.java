package com.megazone.act.cms.application.dto.request;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import lombok.*;

import com.megazone.act.cms.domain.entity.*;
import com.megazone.act.cms.domain.type.*;
import com.megazone.act.cms.domain.vo.*;

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
    private SubmissionType submissionType;
    private CloudServiceType cloudServiceType;
    private DealType dealType;
    private int customerId;

    private long amount;
    private CurrencyUnitType currencyUnitType;
    private boolean hasVat;
    private String amountRemark;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractEndDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate invoiceStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate invoiceEndDate;

    private InvoiceType invoiceType;
    // TODO: 세금계산서 작성일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate taxCreatedDate;
    private String taxReceivedEmail;

    private int contractManagerId;
    private int salesManagerId;
    private String edmLinkUrl;
    private String remark;

    private List<Integer> customerEmployeeIds;
    private List<Integer> pmIds;

    // 증빙 서류
    private MultipartFile contractFile;
    private MultipartFile businessRegistrationFile;
    private MultipartFile secretContractFile;
    private List<MultipartFile> files;
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
            new Period(contractStartDate, contractEndDate),
            new Period(invoiceStartDate, invoiceEndDate),
            new ContractMoney(currencyUnitType, amount, amountRemark),
            employees.stream()
                .map(ContractEmployee::new)
                .toList(),
            edmLinkUrl,
            customer,
            customerEmployees.stream()
                .map(ContractCustomerEmployee::new)
                .toList(),
            getContractDetails()
        );
    }

    private List<ContractDetail> getContractDetails() {
        return Stream.of(infraDetail, psDetail, msDetail, dpDetail)
            .filter(Objects::nonNull)
            .map(it -> new ContractDetail(it.getName(), it.getType()))
            .toList();
    }
}


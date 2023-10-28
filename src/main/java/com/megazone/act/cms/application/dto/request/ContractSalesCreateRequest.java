package com.megazone.act.cms.application.dto.request;

import com.megazone.act.cms.domain.entity.*;
import com.megazone.act.cms.domain.type.*;
import com.megazone.act.cms.domain.vo.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static org.springframework.util.StringUtils.hasText;

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
    private Integer customerId;

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

    private List<ContractEmployeeRequest> contractEmployees = new ArrayList<>();
    private String edmLinkUrl;
    private String remark;

    private List<Integer> customerEmployeeIds = new ArrayList<>();
    private List<Integer> pmIds = new ArrayList<>();

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
                .map(it -> new ContractEmployee(it, getEmployeeRoleTypeById(it.getId())))
                .toList(),
            edmLinkUrl,
            customer,
            customerEmployees.stream()
                .map(ContractCustomerEmployee::new)
                .toList(),
            getContractDetails()
        );
    }

    private EmployeeRoleType getEmployeeRoleTypeById(int id) {
        return contractEmployees.stream()
            .filter(it -> it.getId() == id)
            .map(ContractEmployeeRequest::getType)
            .findAny()
            .orElseThrow();
    }

    private List<ContractDetail> getContractDetails() {
        return Stream.of(infraDetail, psDetail, msDetail, dpDetail)
            .filter(Objects::nonNull)
            .map(it -> new ContractDetail(it.getName(), it.getType()))
            .toList();
    }

    public List<ContractEmployeeRequest> getContractEmployees() {
        return Objects.requireNonNullElse(contractEmployees, Collections.emptyList());
    }

    public List<MultipartFile> files() {
        return Stream.concat(Stream.of(contractFile, businessRegistrationFile, secretContractFile), files.stream())
                .filter(Objects::nonNull)
                .filter(it -> hasText(it.getOriginalFilename()))
                .toList();
    }
}


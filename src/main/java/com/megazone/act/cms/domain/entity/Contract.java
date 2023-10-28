package com.megazone.act.cms.domain.entity;

import com.megazone.act.cms.domain.entity.convertor.ContractStatusConvertor;
import com.megazone.act.cms.domain.type.ContractStatus;
import com.megazone.act.cms.domain.vo.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tb_cntrct_m")
@Entity
public class Contract extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cntrct_id")
    private Integer id;

    @Column(name = "cntrct_no")
    private String no;

    @Column(name = "cntrct_ver")
    private Double version = 1.0;

    @Column(name = "cntrct_nm")
    private String name;

    @Column(name = "rmrk")
    private String remark;

    private String edmLinkUrl;

    @JoinColumn(name = "crprtn_id")
    @ManyToOne
    private Corporation corporation;

    @JoinColumn(name = "customer_id")
    @ManyToOne
    private Customer customer;

    @Embedded
    private ContractTypes contractTypes;

    @Embedded
    private Period contractPeriod;

    @AttributeOverride(name = "startDate", column = @Column(name = "invoice_strt_ymd"))
    @AttributeOverride(name = "endDate", column = @Column(name = "invoice_end_ymd"))
    @Embedded
    private Period invoicePeriod;

    @Embedded
    private ContractMoney contractMoney;

    @Convert(converter = ContractStatusConvertor.class)
    private ContractStatus status = ContractStatus.SAVED;

    @Column(name = "src_system_ref_id")
    private String salesForceContractNo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contract")
    private List<ContractDetail> contractDetails = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contract")
    private List<ContractEmployee> contractEmployees = new ArrayList<>();

    @JoinColumn(name = "contract_id", nullable = false, updatable = false)
    @OneToMany(cascade = CascadeType.ALL)
    private List<ContractCustomerEmployee> contractCustomerEmployees = new ArrayList<>();

    @JoinColumn(name = "contract_id", nullable = false, updatable = false)
    @OneToMany(cascade = CascadeType.ALL)
    private List<AttachmentFile> files = new ArrayList<>();

    public Contract(
        String name,
        String remark,
        String salesForceContractNo,
        Corporation corporation,
        ContractTypes contractTypes,
        Period contractPeriod,
        Period invoicePeriod,
        ContractMoney contractMoney,
        List<ContractEmployee> contractEmployees,
        String edmLinkUrl,
        Customer customer,
        List<ContractCustomerEmployee> contractCustomerEmployees,
        List<ContractDetail> contractDetails
    ) {
        this.name = name;
        this.no = randomNo(contractTypes);
        this.remark = remark;
        this.edmLinkUrl = edmLinkUrl;
        this.salesForceContractNo = salesForceContractNo;
        this.corporation = corporation;
        this.contractTypes = contractTypes;
        this.contractPeriod = contractPeriod;
        this.invoicePeriod = invoicePeriod;
        this.contractMoney = contractMoney;
        contractEmployees.forEach(this::addContractEmployee);
        this.customer = customer;
        contractCustomerEmployees.forEach(this::addContractCustomerEmployee);
        contractDetails.forEach(this::addContractDetail);
    }

    private static String randomNo(ContractTypes contractTypes) {
        return String.format("%s-%s-%s",
                "MZC", contractTypes.codeForNo(), UUID.randomUUID().toString().substring(0, 6).toUpperCase());
    }

    public String getCustomerName() {
        if (customer == null) {
            return "";
        }
        return customer.getName();
    }

    private void addContractEmployee(ContractEmployee contractEmployee) {
        contractEmployee.setContract(this);
        this.contractEmployees.add(contractEmployee);
    }

    private void addContractCustomerEmployee(ContractCustomerEmployee contractCustomerEmployee) {
        this.contractCustomerEmployees.add(contractCustomerEmployee);
    }

    private void addContractDetail(ContractDetail contractDetail) {
        contractDetail.setContract(this);
        contractDetails.add(contractDetail);
    }

    public void addAttachmentFiles(List<AttachmentFile> attachmentFiles) {
        this.files.addAll(attachmentFiles);
    }
}

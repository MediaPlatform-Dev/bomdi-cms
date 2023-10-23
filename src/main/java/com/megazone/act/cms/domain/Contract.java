package com.megazone.act.cms.domain;

import com.megazone.act.cms.domain.type.ContractStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tb_cntrct_m")
@Entity
public class Contract extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cntrct_id")
    private Long id;

    @Column(name = "cntrct_no")
    private String no;

    @Column(name = "cntrct_ver")
    private Double version = 1.0;

    @Column(name = "cntrct_nm")
    private String name;

    @Column(name = "rmrk")
    private String remark;

    @JoinColumn(name = "crprtn_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Corporation corporation;

    @JoinColumn(name = "cstmr_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Customer customer;

    @Embedded
    private ContractTypes contractTypes;

    @Embedded
    private ContractPeriod period;

    @Embedded
    private ContractMoney contractMoney;

    @Enumerated(EnumType.STRING)
    private ContractStatus status = ContractStatus.SAVED;

    @Column(name = "src_system_ref_id")
    private String salesForceContractNo;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "contract")
    private List<ContractDetail> contractDetails = new ArrayList<>();

    @OneToMany
    private List<Employee> employees = new ArrayList<>();

    @OneToMany
    private List<CustomerEmployee> customerEmployees = new ArrayList<>();

    public Contract(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }

    public Contract(
        String name,
        String remark,
        String salesForceContractNo,
        Corporation corporation,
        ContractTypes contractTypes,
        ContractPeriod period,
        ContractMoney contractMoney,
        Customer customer,
        List<Employee> employees,
        List<CustomerEmployee> customerEmployees,
        List<ContractDetail> contractDetails
    ) {
        this.name = name;
        this.remark = remark;
        this.salesForceContractNo = salesForceContractNo;
        this.corporation = corporation;
        this.contractTypes = contractTypes;
        this.period = period;
        this.contractMoney = contractMoney;
        this.customer = customer;
        this.employees = employees;
        this.customerEmployees = customerEmployees;
        this.contractDetails = contractDetails;
    }

    public void update(String name, String contents) {
        this.name = name;
        this.remark = contents;
    }

    public void addContractDetails(List<ContractDetail> contractDetails) {
        contractDetails.forEach(this::addContractDetail);
    }

    private void addContractDetail(ContractDetail contractDetail) {
        contractDetail.setContract(this);
        contractDetails.add(contractDetail);
    }
}

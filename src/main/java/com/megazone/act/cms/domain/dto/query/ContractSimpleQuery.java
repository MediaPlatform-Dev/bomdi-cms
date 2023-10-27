package com.megazone.act.cms.domain.dto.query;

import com.megazone.act.cms.domain.type.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ContractSimpleQuery {
    private int id;
    private ContractStatus status;
    private ContractType contractType;
    private String name;
    private String salesForceContractNo;
    private long amount;
    private LocalDate startDate;
    private LocalDate endDate;
    private String customerName;
    private List<ContractEmployeeQuery> contractEmployees = new ArrayList<>();
    private List<ContractDetailQuery> contractDetails = new ArrayList<>();

    public void addEmployees(List<ContractEmployeeQuery> employees) {
        if (employees == null) {
            return;
        }
        contractEmployees.addAll(employees);
    }

    public void addDetail(List<ContractDetailQuery> details) {
        if (details == null) {
            return;
        }
        contractDetails.addAll(details);
    }

    public String getContractManagerName() {
        return contractEmployees.stream()
            .filter(it -> it.getType().isContract())
            .map(ContractEmployeeQuery::getName)
            .findAny()
            .orElse("");
    }

    public String getSalesManagerName() {
        return contractEmployees.stream()
            .filter(it -> it.getType().isSales())
            .map(ContractEmployeeQuery::getName)
            .findAny()
            .orElse("");
    }
}

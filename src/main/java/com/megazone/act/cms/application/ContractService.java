package com.megazone.act.cms.application;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.megazone.act.cms.application.dto.request.*;
import com.megazone.act.cms.application.dto.response.ContractQuery;
import com.megazone.act.cms.application.dto.response.ContractResponse;
import com.megazone.act.cms.domain.dto.ContractCondition;
import com.megazone.act.cms.domain.entity.*;
import com.megazone.act.cms.domain.repository.*;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ContractService {

    private final ContractRepository contractRepository;
    private final CorporationRepository corporationRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final CustomerEmployeeRepository customerEmployeeRepository;

    @Transactional
    public void createContract(ContractSalesCreateRequest request) {
        Corporation corporation = corporationRepository.getReferenceById(1L);
        List<Employee> employees = Stream.of(request.getContractManagerId(), request.getSalesManagerId())
            .map(employeeRepository::getReferenceById)
            .toList();
        Customer customer = customerRepository.getReferenceById(request.getCustomerId());
        List<CustomerEmployee> customerEmployees = request.getCustomerEmployeeIds().stream()
            .map(customerEmployeeRepository::getReferenceById)
            .toList();

        Contract contract = request.toEntity(corporation, employees, customer, customerEmployees);

        try {
            contractRepository.save(contract);
        } catch (EntityNotFoundException e) {
            log.error("데이터를 찾을 수 없습니다. - ", e);
        }
    }

    public List<ContractQuery> getContracts(ContractCondition condition) {
        return contractRepository.findAllQuery(condition)
            .stream()
            .map(ContractQuery::from)
            .toList();
    }

    public ContractResponse getContract(long contractId) {
        return ContractResponse.from(getContractById(contractId));
    }

    @Transactional
    public void updateContract(Long contractId, ContractSalesUpdateRequest request) {
        Contract contract = getContractById(contractId);
        //contract.update(request.name(), request.contents());
    }

    @Transactional
    public void updateContract(Long contractId, ContractPurchaseUpdateRequest request) {
        Contract contract = getContractById(contractId);
        //contract.update(request.name(), request.contents());
    }

    private Contract getContractById(long contractId) {
        return contractRepository.findById(contractId)
            .orElseThrow(() -> new IllegalArgumentException("계약서를 찾을 수 없습니다. - " + contractId));
    }
}

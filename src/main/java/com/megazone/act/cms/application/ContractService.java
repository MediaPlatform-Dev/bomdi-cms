package com.megazone.act.cms.application;

import com.megazone.act.cms.application.dto.request.*;
import com.megazone.act.cms.application.dto.response.ContractResponse;
import com.megazone.act.cms.application.dto.response.FileResponse;
import com.megazone.act.cms.domain.dto.condition.ContractCondition;
import com.megazone.act.cms.domain.dto.query.ContractSimpleQuery;
import com.megazone.act.cms.domain.entity.*;
import com.megazone.act.cms.domain.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    private final FileStorage fileStorage;

    @Transactional
    public void createContract(ContractSalesCreateRequest request) {
        Contract contract = entityFrom(request);
        List<AttachmentFile> files = fileStorage.upload(request.files())
                .stream()
                .map(it -> (FileResponse) it)
                .map(FileResponse::toEntity)
                .toList();
        contract.addAttachmentFiles(files);

        try {
            contractRepository.save(contract);
        } catch (EntityNotFoundException e) {
            log.error("데이터를 찾을 수 없습니다. - ", e);
        }
    }

    private Contract entityFrom(ContractSalesCreateRequest request) {
        Corporation corporation = corporationRepository.getReferenceById(1L);
        List<Employee> employees = request.getContractEmployees()
            .stream()
            .filter(it -> it.getId() != null)
            .map(it -> employeeRepository.getReferenceById(it.getId()))
            .toList();
        Customer customer = getCustomer(request.getCustomerId());
        List<CustomerEmployee> customerEmployees = request.getCustomerEmployeeIds().stream()
            .map(customerEmployeeRepository::getReferenceById)
            .toList();

        return request.toEntity(corporation, employees, customer, customerEmployees);
    }

    private Customer getCustomer(Integer customerId) {
        if (customerId == null) {
            return null;
        }
        return customerRepository.getReferenceById(customerId);
    }

    public List<ContractSimpleQuery> getContracts(ContractCondition condition) {
        return contractRepository.findAllQuery(condition);
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

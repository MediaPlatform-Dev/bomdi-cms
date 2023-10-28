package com.megazone.act.cms.application;

import com.megazone.act.cms.application.dto.request.*;
import com.megazone.act.cms.domain.port.storage.FileStorage;
import com.megazone.act.cms.infrastructure.storage.FileResponse;
import com.megazone.act.cms.domain.entity.*;
import com.megazone.act.cms.domain.port.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ContractWriteService {

    private final ContractRepository contractRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final FileStorage<FileResponse> fileStorage;

    public void createContract(ContractSalesCreateRequest request) {
        Contract contract = entityFrom(request);
        List<FileResponse> responses = fileStorage.upload(request.files());
        List<AttachmentFile> files = responses.stream()
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
        Corporation corporation = new Corporation(1);
        List<Employee> employees = request.getContractEmployees()
                .stream()
                .filter(it -> it.getId() != null)
                .map(it -> employeeRepository.getReferenceById(it.getId()))
                .toList();

        return request.toEntity(corporation, employees);
    }

    public void updateContract(Long contractId, ContractSalesUpdateRequest request) {
        Contract contract = getContractById(contractId);
    }

    private Contract getContractById(long contractId) {
        return contractRepository.findById(contractId)
            .orElseThrow(() -> new IllegalArgumentException("계약서를 찾을 수 없습니다. - " + contractId));
    }
}

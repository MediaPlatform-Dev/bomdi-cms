package com.megazone.act.cms.application;

import com.megazone.act.cms.application.dto.request.ContractPurchaseUpdateRequest;
import com.megazone.act.cms.application.dto.request.ContractSalesCreateRequest;
import com.megazone.act.cms.application.dto.request.ContractSalesUpdateRequest;
import com.megazone.act.cms.application.dto.response.ContractResponse;
import com.megazone.act.cms.domain.dto.condition.ContractCondition;
import com.megazone.act.cms.domain.dto.query.ContractSimpleQuery;
import com.megazone.act.cms.domain.entity.AttachmentFile;
import com.megazone.act.cms.domain.entity.Contract;
import com.megazone.act.cms.domain.entity.Corporation;
import com.megazone.act.cms.domain.entity.Customer;
import com.megazone.act.cms.domain.entity.CustomerEmployee;
import com.megazone.act.cms.domain.entity.Employee;
import com.megazone.act.cms.domain.port.repository.ContractRepository;
import com.megazone.act.cms.domain.port.repository.CorporationRepository;
import com.megazone.act.cms.domain.port.repository.CustomerEmployeeRepository;
import com.megazone.act.cms.domain.port.repository.CustomerRepository;
import com.megazone.act.cms.domain.port.repository.EmployeeRepository;
import com.megazone.act.cms.domain.port.storage.FileStorage;
import com.megazone.act.cms.infrastructure.storage.FileResponse;
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
public class ContractReadService {

    private final ContractRepository contractRepository;

    public List<ContractSimpleQuery> getContracts(ContractCondition condition) {
        return contractRepository.findAllQuery(condition);
    }

    public ContractResponse getContract(long contractId) {
        return ContractResponse.from(getContractById(contractId));
    }

    private Contract getContractById(long contractId) {
        return contractRepository.findById(contractId)
            .orElseThrow(() -> new IllegalArgumentException("계약서를 찾을 수 없습니다. - " + contractId));
    }
}

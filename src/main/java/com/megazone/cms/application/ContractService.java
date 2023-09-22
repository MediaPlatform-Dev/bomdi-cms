package com.megazone.cms.application;

import com.megazone.cms.application.dto.*;
import com.megazone.cms.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ContractService {

    private final ContractRepository contractRepository;
    private final ContractorRepository contractorRepository;

    @Transactional
    public void create(ContractRequest request) {
        Contractor contractor = contractorRepository.findByName(request.contractor())
            .orElseGet(() -> new Contractor(request.contractor()));

        Contract contract = new Contract(request.name(), request.contents(), contractor);
        contractRepository.save(contract);
    }

    public List<ContractResponse> findAll() {
        return contractRepository.findAll()
            .stream()
            .map(ContractResponse::of)
            .toList();
    }

    public ContractDetailResponse findById(Long contractId) {
        return ContractDetailResponse.of(getById(contractId));
    }

    @Transactional
    public void update(Long contractId, ContractUpdateRequest request) {
        Contract contract = getById(contractId);
        contract.update(request.name(), request.contents());
    }

    private Contract getById(Long contractId) {
        return contractRepository.findById(contractId)
            .orElseThrow(() -> new IllegalArgumentException(contractId + "를 찾을 수 없습니다."));
    }

    @Transactional
    public void delete(Long contractId) {
        contractRepository.deleteById(contractId);
    }
}

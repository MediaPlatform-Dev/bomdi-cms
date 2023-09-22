package com.megazone.bomdi.cms.application;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import com.megazone.bomdi.cms.application.dto.*;
import com.megazone.bomdi.cms.domain.Contract;
import com.megazone.bomdi.cms.domain.Contractor;
import com.megazone.bomdi.cms.repository.ContractRepository;
import com.megazone.bomdi.cms.repository.ContractorRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ContractService {

    private final ContractRepository contractRepository;
    private final ContractorRepository contractorRepository;

    @Transactional
    public void create(ContractCreateRequest request) {
        Contractor contractor = contractorRepository.findByName(request.contractor())
            .orElseGet(() -> new Contractor(request.contractor()));

        Contract contract = new Contract(request.name(), request.contents(), contractor);
        contractRepository.save(contract);
    }

    public List<ContractResponse> findAll() {
        return contractRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
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

package com.megazone.act.cms.application;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import com.megazone.act.cms.application.dto.*;
import com.megazone.act.cms.domain.Contract;
import com.megazone.act.cms.domain.Contractor;
import com.megazone.act.cms.repository.ContractRepository;
import com.megazone.act.cms.repository.ContractorRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ContractService {

    private final ContractRepository contractRepository;
    private final ContractorRepository contractorRepository;

    @Transactional
    public void createContract(ContractCreateRequest request) {
        Contractor contractor = contractorRepository.findByName(request.contractor())
            .orElseGet(() -> new Contractor(request.contractor()));

        Contract contract = new Contract(request.name(), request.contents(), contractor);
        contractRepository.save(contract);
    }

    public List<ContractResponse> getContractList() {
        return contractRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
            .stream()
            .map(ContractResponse::from)
            .toList();
    }

    public ContractDetailResponse getContract(Long contractId) {
        return ContractDetailResponse.from(getContractById(contractId));
    }

    @Transactional
    public void updateContract(Long contractId, ContractUpdateRequest request) {
        Contract contract = getContractById(contractId);
        contract.update(request.name(), request.contents());
    }

    private Contract getContractById(Long contractId) {
        return contractRepository.findById(contractId)
            .orElseThrow(() -> new IllegalArgumentException(contractId + "를 찾을 수 없습니다."));
    }

    @Transactional
    public void deleteContract(Long contractId) {
        contractRepository.deleteById(contractId);
    }
}

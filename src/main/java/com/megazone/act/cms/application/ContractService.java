package com.megazone.act.cms.application;

import com.megazone.act.cms.application.dto.request.*;
import com.megazone.act.cms.application.dto.response.ContractResponse;
import com.megazone.act.cms.domain.*;
import com.megazone.act.cms.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ContractService {

    private final ContractRepository contractRepository;
    private final CorporationRepository corporationRepository;
    private final BusinessPartnerRepository businessPartnerRepository;

    @Transactional
    public void createContract() {
        // TODO: 자사, 고객사 데이터 초기 적재 필요
        //String corporationName = request.getOwnCorporationType().name();
        //Corporation corporation = getCorporationOrElseNew(corporationName);
        //String businessPartnerName = request.getBusinessPartnerName();
        //Customer customer = getBusinessPartnerOrElseNew(businessPartnerName);
        //
        //Contract contract = ContractMapper.INSTANCE.toEntity(corporation, customer, request);
        //
        //List<ContractDetail> contractDetails = request.getContractDetails()
        //    .stream()
        //    .map(it -> new ContractDetail(it.getName(), it.getType()))
        //    .toList();
        //contract.addContractDetails(contractDetails);
        //contractRepository.save(contract);
    }

    private Corporation getCorporationOrElseNew(String corporationName) {
        return corporationRepository.findByName(corporationName)
            .orElseGet(() -> new Corporation(corporationName));
    }

    private Customer getBusinessPartnerOrElseNew(String businessPartnerName) {
        return businessPartnerRepository.findByName(businessPartnerName)
            .orElseGet(() -> new Customer(businessPartnerName));
    }

    public List<ContractResponse> getContractList() {
        return contractRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
            .stream()
            .map(ContractResponse::from)
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
            .orElseThrow(() -> new IllegalArgumentException(contractId + " 계약서를 찾을 수 없습니다."));
    }
}

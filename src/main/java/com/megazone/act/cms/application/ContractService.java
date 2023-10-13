package com.megazone.act.cms.application;

import com.megazone.act.cms.application.dto.*;
import com.megazone.act.cms.domain.*;
import com.megazone.act.cms.domain.repository.*;
import com.megazone.act.cms.domain.type.ContractDetailType;
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
    public void createContract(ContractCreateRequest request) {
        // TODO: 자사, 고객사 데이터 초기 적재 필요
        String corporationName = request.getOwnCorporationType().name();
        Corporation corporation = getCorporationOrElseNew(corporationName);
        String businessPartnerName = request.getBusinessPartnerName();
        BusinessPartner businessPartner = getBusinessPartnerOrElseNew(businessPartnerName);

        ContractTypes contractTypes = new ContractTypes(
            request.getContractType(),
            ContractDetailType.AWS,
            request.getDealType(),
            request.getSubmissionType()
        );
        ContractMoney contractMoney = new ContractMoney(request.getCurrencyUnit(), request.getTotalContractAmount());

        Contract contract = Contract.builder()
            .corporation(corporation)
            .businessPartner(businessPartner)
            .contractTypes(contractTypes)
            .salesForceContractId(request.getSalesForceContractId())
            .name(request.getName())
            .number(request.getBeforeUpdateId())
            .contractMoney(contractMoney)
            .contractorName(request.getContractor())
            .salesPersonName(request.getSalesPersonName())
            .description(request.getDescription())
            .period(new ContractPeriod(request.getContractStartDate(), request.getContractEndDate()))
            .build();

        contract.addContractDetail(new ContractDetail(request.getServiceType()));

        // TODO: 계약 상세 저장하기
        contractRepository.save(contract);
    }

    private Corporation getCorporationOrElseNew(String corporationName) {
        return corporationRepository.findByName(corporationName)
            .orElseGet(() -> new Corporation(corporationName));
    }

    private BusinessPartner getBusinessPartnerOrElseNew(String businessPartnerName) {
        return businessPartnerRepository.findByName(businessPartnerName)
            .orElseGet(() -> new BusinessPartner(businessPartnerName));
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
}

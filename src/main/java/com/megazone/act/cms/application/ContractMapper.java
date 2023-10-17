package com.megazone.act.cms.application;

import com.megazone.act.cms.application.dto.request.ContractCreateRequest;
import com.megazone.act.cms.domain.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContractMapper {

    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

    @Mapping(source = "request.name", target = "name")
    @Mapping(source = "request.description", target = "description")
    @Mapping(source = "corporation", target = "corporation")
    @Mapping(source = "businessPartner", target = "businessPartner")
    @Mapping(source = "request.contractType", target = "contractTypes.contractType")
    @Mapping(source = "request.dealType", target = "contractTypes.dealType")
    @Mapping(source = "request.submissionType", target = "contractTypes.submissionType")
    @Mapping(source = "request.currencyUnit", target = "contractMoney.currencyUnit")
    @Mapping(source = "request.totalContractAmount", target = "contractMoney.totalAmount")
    @Mapping(source = "request.contractStartDate", target = "period.startDate")
    @Mapping(source = "request.contractEndDate", target = "period.endDate")
    @Mapping(source = "request.contractor", target = "contractorName")
    @Mapping(source = "request.salesPersonName", target = "salesPersonName")
    @Mapping(source = "request.salesForceContractId", target = "salesForceContractId")
    Contract toEntity(Corporation corporation, BusinessPartner businessPartner, ContractCreateRequest request);
}

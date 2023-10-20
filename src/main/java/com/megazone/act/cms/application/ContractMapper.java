package com.megazone.act.cms.application;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContractMapper {

    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);
    //
    //@Mapping(source = "request.name", target = "name")
    //@Mapping(source = "request.description", target = "description")
    //@Mapping(source = "corporation", target = "corporation")
    //@Mapping(source = "customer", target = "customer")
    //@Mapping(source = "request.contractType", target = "contractTypes.contractType")
    //@Mapping(source = "request.dealType", target = "contractTypes.dealType")
    //@Mapping(source = "request.submissionType", target = "contractTypes.submissionType")
    //@Mapping(source = "request.currencyUnitType", target = "contractMoney.currencyUnitType")
    //@Mapping(source = "request.totalContractAmount", target = "contractMoney.totalAmount")
    //@Mapping(source = "request.contractStartDate", target = "period.startDate")
    //@Mapping(source = "request.contractEndDate", target = "period.endDate")
    //@Mapping(source = "request.contractor", target = "contractorName")
    //@Mapping(source = "request.salesPersonName", target = "salesPersonName")
    //@Mapping(source = "request.salesForceContractId", target = "salesForceContractId")
    //Contract toEntity(Corporation corporation, Customer customer, ContractCreateRequest request);
}

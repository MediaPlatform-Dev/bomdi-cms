package com.megazone.act.cms.application;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import com.megazone.act.cms.application.dto.response.CustomerResponse;
import com.megazone.act.cms.domain.port.repository.CustomerRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CustomerReadService {

    private final CustomerRepository customerRepository;

    public List<CustomerResponse> getCustomers() {
        return customerRepository.findAll()
            .stream()
            .map(CustomerResponse::from)
            .toList();
    }
}

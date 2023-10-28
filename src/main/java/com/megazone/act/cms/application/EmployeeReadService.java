package com.megazone.act.cms.application;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import com.megazone.act.cms.application.dto.response.EmployeeResponse;
import com.megazone.act.cms.domain.port.repository.EmployeeRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EmployeeReadService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeResponse> getEmployees() {
        return employeeRepository.findAll()
            .stream()
            .map(EmployeeResponse::from)
            .toList();
    }
}

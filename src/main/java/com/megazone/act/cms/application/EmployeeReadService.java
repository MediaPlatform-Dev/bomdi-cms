package com.megazone.act.cms.application;


import com.megazone.act.cms.domain.dto.query.EmployeeQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import com.megazone.act.cms.domain.port.repository.EmployeeRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EmployeeReadService {

    private final EmployeeRepository employeeRepository;

    public Page<EmployeeQuery> getEmployees(Pageable pageable) {
        return employeeRepository.findAllBy(pageable);
    }
}

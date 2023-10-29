package com.megazone.act.cms.web.ui;



import com.megazone.act.cms.domain.dto.query.EmployeeQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.megazone.act.cms.application.EmployeeReadService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EmployeesRestController {

    private final EmployeeReadService employeeReadService;

    @GetMapping("/employees")
    public Page<EmployeeQuery> employees(Pageable pageable) {
        return employeeReadService.getEmployees(pageable);
    }
}

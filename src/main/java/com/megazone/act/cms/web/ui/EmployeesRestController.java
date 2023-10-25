package com.megazone.act.cms.web.ui;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.megazone.act.cms.application.EmployeeService;
import com.megazone.act.cms.application.dto.response.EmployeeResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EmployeesRestController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public List<EmployeeResponse> employees() {
        return employeeService.getEmployees();
    }
}
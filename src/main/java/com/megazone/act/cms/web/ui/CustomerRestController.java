package com.megazone.act.cms.web.ui;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.megazone.act.cms.application.CustomerService;
import com.megazone.act.cms.application.dto.response.CustomerResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerResponse> customers() {
        return customerService.getCustomers();
    }
}

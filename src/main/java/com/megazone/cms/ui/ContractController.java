package com.megazone.cms.ui;

import com.megazone.cms.application.ContractService;
import com.megazone.cms.application.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ContractController {

    private final ContractService contractService;

    @GetMapping("/contracts/form")
    public String contractForm() {
        return "contracts/form";
    }

    @PostMapping("/contracts/form")
    public String postNewContract(@Validated @ModelAttribute ContractRequest contractRequest, BindingResult bindingResult, Model model) {
        contractService.create(contractRequest);
        return "redirect:/contracts";
    }

    @GetMapping("/contracts")
    public String contracts(Model model) {
        List<ContractResponse> contracts = contractService.findAll();
        model.addAttribute("contracts", contracts);
        return "contracts/index";
    }

    @GetMapping("/contracts/{contractId}/form")
    public String contract(@PathVariable Long contractId, Model model) {
        ContractDetailResponse contract = contractService.findById(contractId);
        model.addAttribute("contract", contract);
        return "contracts/update-form";
    }

    @PostMapping("/contracts/{contractId}/update")
    public String updateContract(@PathVariable Long contractId, @ModelAttribute ContractUpdateRequest request) {
        contractService.update(contractId, request);
        return "redirect:/contracts";
    }

    @PostMapping("/contracts/{contractId}/delete")
    public String deleteContract(@PathVariable Long contractId) {
        contractService.delete(contractId);
        return "redirect:/contracts";
    }
}

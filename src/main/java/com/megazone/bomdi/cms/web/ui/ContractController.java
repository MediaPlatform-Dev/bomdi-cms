package com.megazone.bomdi.cms.web.ui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.megazone.bomdi.cms.application.ContractService;
import com.megazone.bomdi.cms.application.dto.*;

@RequiredArgsConstructor
@RequestMapping("/contracts")
@Controller
public class ContractController {

    private final ContractService contractService;

    @GetMapping
    public String list(Model model) {
        List<ContractResponse> contracts = contractService.findAll();
        model.addAttribute("contracts", contracts);
        return "contracts/list";
    }

    @GetMapping("/form")
    public String createForm(Model model) {
        model.addAttribute("form", ContractCreateRequest.empty());
        return "contracts/create-form";
    }

    @PostMapping
    public String create(@Validated ContractCreateRequest contractCreateRequest, BindingResult bindingResult, Model model) {
        contractService.create(contractCreateRequest);
        return "redirect:/contracts";
    }

    @GetMapping("/{contractId}/form")
    public String updateForm(@PathVariable Long contractId, Model model) {
        ContractDetailResponse contract = contractService.findById(contractId);
        model.addAttribute("contract", contract);
        return "contracts/update-form";
    }

    @PutMapping("/{contractId}")
    public String update(@PathVariable Long contractId, ContractUpdateRequest request) {
        contractService.update(contractId, request);
        return "redirect:/contracts";
    }

    @DeleteMapping("/{contractId}")
    public String delete(@PathVariable Long contractId) {
        contractService.delete(contractId);
        return "redirect:/contracts";
    }
}

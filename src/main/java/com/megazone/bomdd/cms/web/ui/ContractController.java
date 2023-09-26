package com.megazone.bomdd.cms.web.ui;

import jakarta.validation.Valid;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.megazone.bomdd.cms.application.ContractService;
import com.megazone.bomdd.cms.application.dto.*;

@RequiredArgsConstructor
@RequestMapping("/contracts")
@Controller
public class ContractController {

    private static final String REDIRECT_CONTRACTS = "redirect:/contracts";
    private static final String FORM = "form";
    private static final String CONTRACT = "contract";

    private final ContractService contractService;

    @GetMapping
    public String list(Model model) {
        List<ContractResponse> contracts = contractService.getContractList();
        model.addAttribute("contracts", contracts);
        return "contracts/list";
    }

    @GetMapping("/form")
    public String createForm(@ModelAttribute(FORM) ContractCreateRequest emptyForm) {
        return "contracts/create-form";
    }

    @PostMapping("/form")
    public String create(
        @Valid @ModelAttribute(FORM) ContractCreateRequest createForm,
        BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "contracts/create-form";
        }

        contractService.createContract(createForm);
        return REDIRECT_CONTRACTS;
    }

    @GetMapping("/{contractId}/form")
    public String updateForm(
        @PathVariable Long contractId,
        @ModelAttribute(FORM) ContractUpdateRequest emptyForm,
        Model model
    ) {
        model.addAttribute(CONTRACT, contractService.getContract(contractId));
        return "contracts/update-form";
    }

    @PutMapping("/{contractId}/form")
    public String update(
        @PathVariable Long contractId,
        @Valid @ModelAttribute(FORM) ContractUpdateRequest updateForm,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(CONTRACT, ContractDetailResponse.of(contractId, updateForm));
            return "contracts/update-form";
        }

        contractService.updateContract(contractId, updateForm);
        return REDIRECT_CONTRACTS;
    }

    @DeleteMapping("/{contractId}")
    public String delete(@PathVariable Long contractId) {
        contractService.deleteContract(contractId);
        return REDIRECT_CONTRACTS;
    }
}

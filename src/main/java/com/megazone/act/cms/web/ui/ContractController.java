package com.megazone.act.cms.web.ui;

import com.megazone.act.cms.application.ContractReadService;
import com.megazone.act.cms.application.ContractWriteService;
import com.megazone.act.cms.application.dto.request.*;
import com.megazone.act.cms.application.dto.response.ContractResponse;
import com.megazone.act.cms.domain.dto.condition.ContractCondition;
import com.megazone.act.cms.domain.dto.query.ContractSimpleQuery;
import com.megazone.act.cms.domain.type.ContractType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/contracts")
@Controller
public class ContractController {

    private static final String REDIRECT_CONTRACTS = "redirect:/contracts";
    private static final String FORM = "form";
    private static final String CONTRACT = "contract";

    private final ContractWriteService contractWriteService;
    private final ContractReadService contractReadService;

    @GetMapping("/sales-form")
    public String createSalesForm(Model model) {
        model.addAttribute(FORM, ContractSalesCreateRequest.EMPTY);
        return "contracts/create-sales-form";
    }

    @PostMapping("/sales-form")
    public String createSales(
        @Valid @ModelAttribute(FORM) ContractSalesCreateRequest createForm,
        BindingResult bindingResult, Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(FORM, createForm);
            return "contracts/create-sales-form";
        }

        contractWriteService.createContract(createForm);
        return REDIRECT_CONTRACTS;
    }

    @GetMapping
    public String list(@ModelAttribute ContractCondition condition, Model model) {
        List<ContractSimpleQuery> contracts = contractReadService.getContracts(condition);
        model.addAttribute("contracts", contracts);
        model.addAttribute("condition", condition);
        return "contracts/list";
    }

    @GetMapping("/{contractId}")
    public String detail(@PathVariable long contractId, Model model) {
        model.addAttribute(CONTRACT, contractReadService.getContract(contractId));
        return "contracts/detail";
    }

    @GetMapping("/{contractId}/update-form")
    public String updateForm(
        @PathVariable long contractId,
        Model model
    ) {
        ContractResponse contract = contractReadService.getContract(contractId);

        model.addAttribute(CONTRACT, contract);
        if (contract.type() == ContractType.SALES) {
            model.addAttribute(FORM, new ContractSalesUpdateRequest());
            return "contracts/update-sales-form";
        }

        model.addAttribute(FORM, new ContractPurchaseUpdateRequest());
        return "contracts/update-purchase-form";
    }

    @PutMapping("/{contractId}/update-sales-form")
    public String updateSales(
        @PathVariable Long contractId,
        @Valid @ModelAttribute(FORM) ContractSalesUpdateRequest updateForm,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(FORM, updateForm);
            return "contracts/update-sales-form";
        }

        contractWriteService.updateContract(contractId, updateForm);
        return REDIRECT_CONTRACTS;
    }
}

package com.megazone.act.cms.web.ui;

import jakarta.validation.Valid;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.megazone.act.cms.application.ContractService;
import com.megazone.act.cms.application.FileService;
import com.megazone.act.cms.application.dto.request.*;
import com.megazone.act.cms.application.dto.response.ContractDetailResponse;
import com.megazone.act.cms.domain.type.ContractType;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/contracts")
@Controller
public class ContractController {

    private static final String REDIRECT_CONTRACTS = "redirect:/contracts";
    private static final String FORM = "form";
    private static final String CONTRACT = "contract";

    private final ContractService contractService;

    private final FileService fileService;

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

        contractService.createContract(createForm);
        fileService.fileUpload(createForm.getContractFile());

        return REDIRECT_CONTRACTS;
    }

    //@GetMapping("/purchase-form")
    //public String createPurchaseForm(Model model) {
    //    model.addAttribute(FORM, ContractCreateRequest.EMPTY);
    //    return "contracts/create-purchase-form";
    //}
    //
    //@PostMapping("/purchase-form")
    //public String createPurchase(
    //        @Valid @ModelAttribute(FORM) ContractCreateRequest createForm,
    //        BindingResult bindingResult, Model model
    //) {
    //    if (bindingResult.hasErrors()) {
    //        model.addAttribute(FORM, createForm);
    //        return "contracts/create-purchase-form";
    //    }
    //
    //    contractService.createContract(createForm);
    //    return REDIRECT_CONTRACTS;
    //}

    @GetMapping
    public String list(Model model) {
        List<ContractDetailResponse> contracts = contractService.getContractList();
        model.addAttribute("contracts", contracts);
        return "contracts/list";
    }

    @GetMapping("/{contractId}")
    public String detail(@PathVariable long contractId, Model model) {
        model.addAttribute(CONTRACT, contractService.getContract(contractId));
        return "contracts/detail";
    }

    @GetMapping("/{contractId}/update-form")
    public String updateForm(
        @PathVariable long contractId,
        Model model
    ) {
        ContractDetailResponse contract = contractService.getContract(contractId);

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

        contractService.updateContract(contractId, updateForm);
        return REDIRECT_CONTRACTS;
    }

    //@PutMapping("/{contractId}/update-purchase-form")
    //public String updatePurchase(
    //    @PathVariable Long contractId,
    //    @Valid @ModelAttribute(FORM) ContractUpdateRequest updateForm,
    //    BindingResult bindingResult,
    //    Model model
    //) {
    //    if (bindingResult.hasErrors()) {
    //        model.addAttribute(CONTRACT, updateForm);
    //        return "contracts/update-purchase-form";
    //    }
    //
    //    contractService.updateContract(contractId, updateForm);
    //    return REDIRECT_CONTRACTS;
    //}
}

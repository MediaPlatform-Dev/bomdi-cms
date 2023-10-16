package com.megazone.act.cms.web.ui;

import com.megazone.act.cms.application.ContractService;
import com.megazone.act.cms.application.dto.request.ContractCreateRequest;
import com.megazone.act.cms.application.dto.request.ContractUpdateRequest;
import com.megazone.act.cms.application.dto.response.ContractDetailResponse;
import com.megazone.act.cms.application.dto.response.ContractResponse;
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

    private final ContractService contractService;

    @GetMapping("/form")
    public String createTypeForm(@RequestParam String type, Model model) {
        if ("sales".equals(type)) {
            model.addAttribute("isSales", true);
        }
        model.addAttribute(FORM, ContractCreateRequest.EMPTY);
        return "contracts/create-form";
    }

    @PostMapping("/form")
    public String create(
        @Valid @ModelAttribute(FORM) ContractCreateRequest createForm,
        BindingResult bindingResult, Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(FORM, createForm);
            return "contracts/create-form";
        }

        contractService.createContract(createForm);
        return REDIRECT_CONTRACTS;
    }

    @GetMapping
    public String list(Model model) {
        List<ContractResponse> contracts = contractService.getContractList();
        model.addAttribute("contracts", contracts);
        return "contracts/list";
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
}

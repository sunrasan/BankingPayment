package com.example.banking.controller;

import com.example.banking.model.BankAccount;
import com.example.banking.repository.BankAccountRepository;
import com.example.banking.repository.BankingProcedureRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final BankAccountRepository accountRepository;
    private final BankingProcedureRepository procedureRepository;

    public AccountController(BankAccountRepository accountRepository,
                             BankingProcedureRepository procedureRepository) {
        this.accountRepository = accountRepository;
        this.procedureRepository = procedureRepository;
    }

    @GetMapping
    public List<BankAccount> getAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("/{id}")
    public BankAccount getAccount(@PathVariable Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Account not found"));
    }

    @GetMapping("/{id}/summary-procedure")
    public Map<String, Object> getSummaryFromProcedure(@PathVariable Long id) {
        return procedureRepository.getAccountSummary(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<BankAccount> getCustomerAccounts(@PathVariable Long customerId) {
        return accountRepository.findByCustomerCustomerId(customerId);
    }
}

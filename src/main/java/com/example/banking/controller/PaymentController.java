package com.example.banking.controller;

import com.example.banking.model.PaymentTxn;
import com.example.banking.repository.BankingProcedureRepository;
import com.example.banking.repository.PaymentTxnRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final BankingProcedureRepository procedureRepository;
    private final PaymentTxnRepository paymentRepository;

    public PaymentController(BankingProcedureRepository procedureRepository,
                             PaymentTxnRepository paymentRepository) {
        this.procedureRepository = procedureRepository;
        this.paymentRepository = paymentRepository;
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> transfer(@Valid @RequestBody TransferRequest request) {
        try {
            Long paymentId = procedureRepository.transferFunds(
                    request.getFromAccountId(), request.getToAccountId(),
                    request.getAmount(), request.getReferenceText());
            Map<String, Object> response = new HashMap<String, Object>();
            response.put("paymentId", paymentId);
            response.put("status", "SUCCESS");
            response.put("message", "Transfer processed by Oracle package procedure");
            return response;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Transfer failed: " + ex.getMessage(), ex);
        }
    }

    @GetMapping("/account/{accountId}")
    public List<PaymentTxn> getAccountPayments(@PathVariable Long accountId) {
        return paymentRepository.findByFromAccountIdOrToAccountIdOrderByCreatedAtDesc(
                accountId, accountId);
    }

    public static class TransferRequest {
        @NotNull
        private Long fromAccountId;
        @NotNull
        private Long toAccountId;
        @NotNull
        @DecimalMin(value = "0.01")
        private BigDecimal amount;
        @NotBlank
        private String referenceText;

        public Long getFromAccountId() { return fromAccountId; }
        public void setFromAccountId(Long fromAccountId) { this.fromAccountId = fromAccountId; }
        public Long getToAccountId() { return toAccountId; }
        public void setToAccountId(Long toAccountId) { this.toAccountId = toAccountId; }
        public BigDecimal getAmount() { return amount; }
        public void setAmount(BigDecimal amount) { this.amount = amount; }
        public String getReferenceText() { return referenceText; }
        public void setReferenceText(String referenceText) { this.referenceText = referenceText; }
    }
}

package com.example.banking.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PAYMENT_TXN")
public class PaymentTxn {
    @Id
    @Column(name = "PAYMENT_ID")
    private Long paymentId;

    @Column(name = "FROM_ACCOUNT_ID", nullable = false)
    private Long fromAccountId;

    @Column(name = "TO_ACCOUNT_ID", nullable = false)
    private Long toAccountId;

    @Column(name = "AMOUNT", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "CURRENCY_CODE", nullable = false, length = 3)
    private String currencyCode;

    @Column(name = "PAYMENT_STATUS", nullable = false, length = 20)
    private String paymentStatus;

    @Column(name = "REFERENCE_TEXT", nullable = false, length = 80)
    private String referenceText;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    public PaymentTxn() {}

    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }
    public Long getFromAccountId() { return fromAccountId; }
    public void setFromAccountId(Long fromAccountId) { this.fromAccountId = fromAccountId; }
    public Long getToAccountId() { return toAccountId; }
    public void setToAccountId(Long toAccountId) { this.toAccountId = toAccountId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getCurrencyCode() { return currencyCode; }
    public void setCurrencyCode(String currencyCode) { this.currencyCode = currencyCode; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public String getReferenceText() { return referenceText; }
    public void setReferenceText(String referenceText) { this.referenceText = referenceText; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

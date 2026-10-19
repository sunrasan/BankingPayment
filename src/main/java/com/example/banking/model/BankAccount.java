package com.example.banking.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "BANK_ACCOUNT")
public class BankAccount {
    @Id
    @Column(name = "ACCOUNT_ID")
    private Long accountId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @Column(name = "ACCOUNT_NUMBER", nullable = false, unique = true, length = 24)
    private String accountNumber;

    @Column(name = "ACCOUNT_TYPE", nullable = false, length = 20)
    private String accountType;

    @Column(name = "CURRENCY_CODE", nullable = false, length = 3)
    private String currencyCode;

    @Column(name = "AVAILABLE_BALANCE", nullable = false, precision = 15, scale = 2)
    private BigDecimal availableBalance;

    @Column(name = "ACCOUNT_STATUS", nullable = false, length = 20)
    private String accountStatus;

    public BankAccount() {}

    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public String getCurrencyCode() { return currencyCode; }
    public void setCurrencyCode(String currencyCode) { this.currencyCode = currencyCode; }
    public BigDecimal getAvailableBalance() { return availableBalance; }
    public void setAvailableBalance(BigDecimal availableBalance) { this.availableBalance = availableBalance; }
    public String getAccountStatus() { return accountStatus; }
    public void setAccountStatus(String accountStatus) { this.accountStatus = accountStatus; }
}

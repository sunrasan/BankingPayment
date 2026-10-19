package com.example.banking.repository;

import com.example.banking.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByCustomerCustomerId(Long customerId);
}

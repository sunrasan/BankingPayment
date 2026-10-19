package com.example.banking.repository;

import com.example.banking.model.PaymentTxn;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentTxnRepository extends JpaRepository<PaymentTxn, Long> {
    List<PaymentTxn> findByFromAccountIdOrToAccountIdOrderByCreatedAtDesc(Long fromId, Long toId);
}

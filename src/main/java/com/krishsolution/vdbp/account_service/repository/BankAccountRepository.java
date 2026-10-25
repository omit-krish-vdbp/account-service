package com.krishsolution.vdbp.account_service.repository;

import com.krishsolution.vdbp.account_service.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository
        extends JpaRepository<BankAccount, Long> {

    boolean existsByAccountNumber(String accountNumber);

    List<BankAccount> findByAuthUserId(String authUserId);
}
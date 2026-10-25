package com.krishsolution.vdbp.account_service.service;

import com.krishsolution.vdbp.account_service.dto.AccountResponse;
import com.krishsolution.vdbp.account_service.dto.CreateAccountRequest;
import com.krishsolution.vdbp.account_service.entity.BankAccount;
import com.krishsolution.vdbp.account_service.enums.AccountCurrency;
import com.krishsolution.vdbp.account_service.enums.AccountStatus;
import com.krishsolution.vdbp.account_service.repository.BankAccountRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.SecureRandom;

@Service
public class AccountService {

    private final BankAccountRepository accountRepository;
    private final SecureRandom secureRandom = new SecureRandom();

    public AccountService(BankAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountResponse createAccount(CreateAccountRequest request) {

        BankAccount account = new BankAccount();

        account.setAuthUserId(request.authUserId());
        account.setAccountType(request.accountType());
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCurrency(AccountCurrency.INR);
        account.setAccountNumber(generateUniqueAccountNumber());

        // Opening balance is zero; never accept it from this request.
        account.setOpeningBalance(BigDecimal.ZERO);

        BankAccount savedAccount = accountRepository.save(account);

        return mapToResponse(savedAccount);
    }

    private String generateUniqueAccountNumber() {

        for (int attempt = 0; attempt < 10; attempt++) {

            // Generates a 12-digit account number.
            StringBuilder number = new StringBuilder("2");

            for (int i = 0; i < 11; i++) {
                number.append(secureRandom.nextInt(10));
            }

            String accountNumber = number.toString();

            if (!accountRepository.existsByAccountNumber(accountNumber)) {
                return accountNumber;
            }
        }

        throw new IllegalStateException(
                "Unable to generate a unique account number. Please try again."
        );
    }

    private AccountResponse mapToResponse(BankAccount account) {

        return new AccountResponse(
                account.getId(),
                account.getAuthUserId(),
                account.getAccountNumber(),
                account.getAccountType(),
                account.getAccountStatus(),
                account.getCurrency(),
                account.getBalance(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }

}
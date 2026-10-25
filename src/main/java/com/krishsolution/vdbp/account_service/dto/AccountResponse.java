package com.krishsolution.vdbp.account_service.dto;

import com.krishsolution.vdbp.account_service.enums.AccountCurrency;
import com.krishsolution.vdbp.account_service.enums.AccountStatus;
import com.krishsolution.vdbp.account_service.enums.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountResponse(
        Long id,
        String authUserId,
        String accountNumber,
        AccountType accountType,
        AccountStatus accountStatus,
        AccountCurrency currency,
        BigDecimal balance,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
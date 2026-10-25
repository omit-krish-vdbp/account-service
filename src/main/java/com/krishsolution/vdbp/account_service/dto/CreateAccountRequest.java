package com.krishsolution.vdbp.account_service.dto;

import com.krishsolution.vdbp.account_service.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAccountRequest(

        @NotBlank(message = "Auth user ID is required")
        @Size(max = 100, message = "Auth user ID must not exceed 100 characters")
        String authUserId,

        @NotNull(message = "Account type is required")
        AccountType accountType

) {
}
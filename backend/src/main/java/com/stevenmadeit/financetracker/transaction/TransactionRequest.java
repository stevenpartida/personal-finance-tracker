package com.stevenmadeit.financetracker.transaction;

import com.stevenmadeit.financetracker.shared.MoneyFlowType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record TransactionRequest(
        @NotNull UUID categoryId,
        @NotNull MoneyFlowType type,
        @NotNull @Positive BigDecimal amount,
        @NotNull Instant occurredOn
        ) {
}

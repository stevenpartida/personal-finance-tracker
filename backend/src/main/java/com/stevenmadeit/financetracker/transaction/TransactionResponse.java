package com.stevenmadeit.financetracker.transaction;

import com.stevenmadeit.financetracker.category.CategoryResponse;
import com.stevenmadeit.financetracker.shared.MoneyFlowType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record TransactionResponse(
        UUID id,
        UUID categoryId,
        String name,
        MoneyFlowType type,
        BigDecimal amount,
        Instant occurredOn,
        Instant createdAt,
        com.stevenmadeit.financetracker.category.CategoryResponse category
) {
}

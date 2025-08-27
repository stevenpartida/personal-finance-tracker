package com.stevenmadeit.financetracker.category;

import com.stevenmadeit.financetracker.shared.MoneyFlowType;

import java.util.UUID;

public record CategoryResponse(
        UUID id,
        String name,
        MoneyFlowType type,
        String icon,
        String color
) {
}

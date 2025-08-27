package com.stevenmadeit.financetracker.category;

import com.stevenmadeit.financetracker.shared.MoneyFlowType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        @NotBlank String name,
        @NotNull MoneyFlowType type,
        String icon,
        String color
) {
}

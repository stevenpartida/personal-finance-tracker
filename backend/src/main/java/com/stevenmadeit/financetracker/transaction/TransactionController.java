package com.stevenmadeit.financetracker.transaction;

import com.stevenmadeit.financetracker.shared.MoneyFlowType;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<TransactionResponse> list(
            @RequestHeader("X-User-Id") UUID userId,
            @RequestParam(required = false) Instant from,
            @RequestParam(required = false) Instant to,
            @RequestParam(required = false) MoneyFlowType type

            ) {
        return service.list(userId, from, to, type);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse create(
            @RequestHeader("X-User-Id") UUID userId,
            @Valid @RequestBody TransactionRequest req
    ) {
        return service.create(userId, req);
    }
}

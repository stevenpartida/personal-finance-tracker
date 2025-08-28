package com.stevenmadeit.financetracker.transaction;

import com.stevenmadeit.financetracker.category.Category;
import com.stevenmadeit.financetracker.category.CategoryRepository;
import com.stevenmadeit.financetracker.shared.MoneyFlowType;
import com.stevenmadeit.financetracker.user.User;
import com.stevenmadeit.financetracker.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository repo;
    private final UserRepository users;
    private final CategoryRepository categories;

    public TransactionService(TransactionRepository repo, UserRepository users, CategoryRepository categories) {
        this.repo = repo;
        this.users = users;
        this.categories = categories;
    }

    @Transactional(readOnly = true)
    public List<TransactionResponse> list(UUID userId, Instant from, Instant to, MoneyFlowType type) {
        List<Transaction> found;

        // If both time window and type are provided, filter by both.
        if (from != null && to != null && type != null) {
            found = repo.findByUserIdAndOccurredOnBetweenOrderByOccurredOnDesc(userId, from, to)
                    .stream().filter(t -> t.getType() == type).toList();
        } else if (from != null && to != null) {
            found = repo.findByUserIdAndOccurredOnBetweenOrderByOccurredOnDesc(userId, from, to);
        } else if (type != null) {
            found = repo.findByUserIdAndTypeOrderByOccurredOnDesc(userId, type);
        } else {
            found = repo.findByUserIdOrderByOccurredOnDesc(userId);
        }

        return found.stream().map(this::toResponse).toList();
    }

    @Transactional
    public TransactionResponse create(UUID userId, TransactionRequest req) {
        User user = users.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));

        // Validate name (required)
        String name = req.name();
        if (name == null || name.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction name is required");
        }

        // Validate amount
        BigDecimal amt = req.amount();
        if (amt == null || amt.signum() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount must be > 0");
        }

        // Validate category belongs to user or is global, and type matches
        Category category = categories.findById(req.categoryId())
                .filter(c -> c.getUser() == null || userId.equals(c.getUser().getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid category"));

        MoneyFlowType type = category.getType();
        Instant occurred = Objects.requireNonNullElse(req.occurredOn(), Instant.now());

        // Create the transaction (make sure your Transaction constructor has 'name' first now)
        Transaction tx = new Transaction(
                user,
                category,
                name,     // NEW: transaction name
                amt,
                type,
                occurred
        );

        Transaction saved = repo.save(tx);
        return toResponse(saved);
    }

    private TransactionResponse toResponse(Transaction t) {
        return new TransactionResponse(
                t.getId(),
                t.getCategory().getId(),
                t.getName(),
                t.getType(),
                t.getAmount(),
                t.getOccurredOn(),
                t.getCreatedAt()
        );
    }
}
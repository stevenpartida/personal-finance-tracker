package com.stevenmadeit.financetracker.transaction;

import com.stevenmadeit.financetracker.shared.MoneyFlowType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.UUID;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByUserIdOrderByOccurredOnDesc(UUID userId);
    List<Transaction> findByUserIdAndOccurredOnBetweenOrderByOccurredOnDesc(
            UUID userId, Instant from, Instant to
    );
    List<Transaction> findByUserIdAndTypeOrderByOccurredOnDesc(UUID userId, MoneyFlowType type);
}

package com.stevenmadeit.financetracker.transaction;

import com.stevenmadeit.financetracker.category.Category;
import com.stevenmadeit.financetracker.shared.MoneyFlowType;
import com.stevenmadeit.financetracker.user.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table (name = "transactions",
        indexes = {
            @Index(name = "idx_tx_user_date", columnList = "user_id, occurred_on"),
                @Index(name = "idx_tx_user_cat_date", columnList = "user_id, category_id, occurred_on" )
        }
)
public class Transaction {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MoneyFlowType type;

    @Column(name = "occurred_on", nullable = false)
    private Instant occurredOn;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    public Transaction() {
    }

    public Transaction(User user, Category category, String name, BigDecimal amount, MoneyFlowType type, Instant occurredOn) {
        this.user = user;
        this.category = category;
        this.name = name;
        this.amount = amount;
        this.type = type;
        this.occurredOn = occurredOn;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public MoneyFlowType getType() {
        return type;
    }

    public void setType(MoneyFlowType type) {
        this.type = type;
    }

    public Instant getOccurredOn() {
        return occurredOn;
    }

    public void setOccurredOn(Instant occurredOn) {
        this.occurredOn = occurredOn;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

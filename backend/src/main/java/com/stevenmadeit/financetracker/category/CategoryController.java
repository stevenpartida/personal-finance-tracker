package com.stevenmadeit.financetracker.category;

import com.stevenmadeit.financetracker.shared.MoneyFlowType;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    // List visible categories (user-owned + global). Optional ?type=EXPENSE|INCOME
    @GetMapping
    public List<CategoryResponse> list(@RequestHeader("X-User-Id") UUID userId,
                                       @RequestParam(required = false) MoneyFlowType type) {
        return service.list(userId, type);
    }

    // Create a user-owned category
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse create(@RequestHeader("X-User-Id") UUID userId,
                                   @Valid @RequestBody CategoryRequest req) {
        return service.create(userId, req);
    }
}
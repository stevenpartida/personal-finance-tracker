package com.stevenmadeit.financetracker.category;

import com.stevenmadeit.financetracker.shared.MoneyFlowType;
import com.stevenmadeit.financetracker.user.User;
import com.stevenmadeit.financetracker.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository repo;
    private final UserRepository users;

    public CategoryService(CategoryRepository repo, UserRepository users) {
        this.repo = repo;
        this.users = users;
    }

    @Transactional
    public CategoryResponse create(UUID userId, CategoryRequest req) {
        String name = req.name() == null ? "" : req.name().trim();

        // Prevent duplicates against BOTH user-owned and global categories
        boolean existsVisible = repo.existsVisibleByNameAndType(userId, name, req.type());
        if (existsVisible) {
            throw new IllegalArgumentException("Category already exists");
        }

        User owner = users.findById(userId).orElseThrow();
        Category cat = new Category(owner, name, req.type(), req.icon(), req.color());
        Category saved = repo.save(cat);

        return new CategoryResponse(
                saved.getId(),
                saved.getName(),
                saved.getType(),
                saved.getIcon(),
                saved.getColor()
        );
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> list(UUID userId, MoneyFlowType type) {
        var list = (type == null)
                ? repo.findByUserIdOrUserIsNullOrderByName(userId)
                : repo.findByUserIdOrUserIsNullAndTypeOrderByName(userId, type);

        return list.stream()
                .map(c -> new CategoryResponse(
                        c.getId(),
                        c.getName(),
                        c.getType(),
                        c.getIcon(),
                        c.getColor()))
                .toList();
    }
}
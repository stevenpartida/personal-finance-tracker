package com.stevenmadeit.financetracker.category;

import com.stevenmadeit.financetracker.shared.MoneyFlowType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findByUserIdOrUserIsNullOrderByName(UUID userId);
    List<Category> findByUserIdOrUserIsNullAndTypeOrderByName(UUID userId, MoneyFlowType type);

    @Query("""
           select case when count(c) > 0 then true else false end
           from Category c
           where (c.user.id = :userId or c.user is null)
             and lower(c.name) = lower(:name)
             and c.type = :type
           """)
    boolean existsVisibleByNameAndType(@Param("userId") UUID userId,
                                       @Param("name") String name,
                                       @Param("type") MoneyFlowType type);

    boolean existsByUserIdAndNameIgnoreCaseAndType(UUID userId, String name, MoneyFlowType type);
}

package com.example.demo.repository;

import com.example.demo.entity.EmojiMapping;
import com.example.demo.enums.GameCategory;
import com.example.demo.repository.projection.CategoryAnalyticsProjection;
import com.example.demo.repository.projection.CategoryHintUsageProjection;
import com.example.demo.dto.CategoryCountSummary;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmojiMappingRepository extends JpaRepository<EmojiMapping, Long> {

        List<EmojiMapping> findByCategoryAndActiveTrue(GameCategory category);

        @Query("SELECT e FROM EmojiMapping e WHERE e.active = true AND " +
                        "(LOWER(e.answer) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                        "LOWER(e.hint) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                        "LOWER(e.alternativeAnswers) LIKE LOWER(CONCAT('%', :keyword, '%'))) ")
        List<EmojiMapping> findByKeyword(String keyword);

        @Query(value = "SELECT * FROM emoji_mappings WHERE category = :category AND active = true ORDER BY RAND()", nativeQuery = true)
        List<EmojiMapping> findRandomByCategory(String category);

        Page<EmojiMapping> findAllByActiveTrueOrderByAccessCountDesc(org.springframework.data.domain.Pageable pageable);

        List<EmojiMapping> findTop10ByActiveTrueOrderByAccessCountDesc();

        Page<EmojiMapping> findAllByActiveTrueOrderBySolveCountDesc(org.springframework.data.domain.Pageable pageable);

        List<EmojiMapping> findTop10ByActiveTrueOrderBySolveCountDesc();

        Page<EmojiMapping> findAllByActiveTrueOrderByHintUsageCountDesc(
                        org.springframework.data.domain.Pageable pageable);

        List<EmojiMapping> findTop10ByActiveTrueOrderByHintUsageCountDesc();

        // Aggregation query: sum accessCount, solveCount, and hintUsageCount grouped by
        // category
        @Query("SELECT e.category as category, " +
                        "SUM(e.accessCount) as totalAccessCount, " +
                        "SUM(e.solveCount) as totalSolveCount, " +
                        "SUM(e.hintUsageCount) as totalHintUsageCount " +
                        "FROM EmojiMapping e WHERE e.active = true " +
                        "GROUP BY e.category ORDER BY totalAccessCount DESC")
        List<CategoryCountSummary> findCategoryCountSummaryOrderByAccessCountDesc();

        @Query("SELECT e.category as category, " +
                        "SUM(e.accessCount) as totalAccessCount, " +
                        "SUM(e.solveCount) as totalSolveCount, " +
                        "SUM(e.hintUsageCount) as totalHintUsageCount " +
                        "FROM EmojiMapping e WHERE e.active = true " +
                        "GROUP BY e.category ORDER BY totalSolveCount DESC")
        List<CategoryCountSummary> findCategoryCountSummaryOrderBySolveCountDesc();

        @Query("SELECT e.category AS category, " +
                        "SUM(e.accessCount) AS totalAccess, " +
                        "SUM(e.solveCount) AS totalSolved, " +
                        "SUM(e.hintUsageCount) AS totalHintsUsed " +
                        "FROM EmojiMapping e " +
                        "GROUP BY e.category")
        List<CategoryAnalyticsProjection> findCategoryAccessSolveAndHintCounts();

        List<EmojiMapping> findTop10ByOrderByAccessCountDesc();

        @Query("SELECT e.category as category, SUM(e.hintUsageCount) as totalHints " +
                        "FROM EmojiMapping e WHERE e.active = true GROUP BY e.category ORDER BY totalHints DESC")
        List<CategoryHintUsageProjection> findHintUsageByCategory();

        @Query("SELECT e.category AS category, " +
                        "SUM(e.accessCount) AS totalAccess, " +
                        "SUM(e.solveCount) AS totalSolved " +
                        "FROM EmojiMapping e " +
                        "WHERE e.active = true " +
                        "GROUP BY e.category")
        List<CategoryAnalyticsProjection> findCategoryAccessAndSolveCounts();

}

package com.example.demo.repository;

import com.example.demo.entity.EmojiMapping;
import com.example.demo.enums.GameCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmojiMappingRepository extends JpaRepository<EmojiMapping, Long> {
    
    List<EmojiMapping> findByCategoryAndActiveTrue(GameCategory category);
    
    @Query("SELECT e FROM EmojiMapping e WHERE e.active = true AND " +
           "(LOWER(e.answer) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.hint) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.alternativeAnswers) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<EmojiMapping> findByKeyword(@Param("keyword") String keyword);
    
    // FIXED: Using Pageable instead of LIMIT parameter
    @Query(value = "SELECT * FROM emoji_mappings WHERE category = :category AND active = true ORDER BY RAND()", 
           nativeQuery = true)
    List<EmojiMapping> findRandomByCategory(@Param("category") String category);
}
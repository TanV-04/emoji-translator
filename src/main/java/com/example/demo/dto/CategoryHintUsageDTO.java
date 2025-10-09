package com.example.demo.dto;

import com.example.demo.enums.GameCategory;

public class CategoryHintUsageDTO {

    private GameCategory category;
    private Long totalHints;

    public CategoryHintUsageDTO() {
        // Default constructor
    }

    public CategoryHintUsageDTO(GameCategory category, Long totalHints) {
        this.category = category;
        this.totalHints = totalHints;
    }

    public GameCategory getCategory() {
        return category;
    }

    public void setCategory(GameCategory category) {
        this.category = category;
    }

    public Long getTotalHints() {
        return totalHints;
    }

    public void setTotalHints(Long totalHints) {
        this.totalHints = totalHints;
    }
}

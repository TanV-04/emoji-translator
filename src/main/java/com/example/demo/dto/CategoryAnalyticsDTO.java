package com.example.demo.dto;

import com.example.demo.enums.GameCategory;

public class CategoryAnalyticsDTO {

    private GameCategory category;
    private Long totalAccess;
    private Long totalSolved;

    // Constructor
    public CategoryAnalyticsDTO(GameCategory category, Long totalAccess, Long totalSolved) {
        this.category = category;
        this.totalAccess = totalAccess;
        this.totalSolved = totalSolved;
    }

    // Getters and Setters
    public GameCategory getCategory() {
        return category;
    }

    public void setCategory(GameCategory category) {
        this.category = category;
    }

    public Long getTotalAccess() {
        return totalAccess;
    }

    public void setTotalAccess(Long totalAccess) {
        this.totalAccess = totalAccess;
    }

    public Long getTotalSolved() {
        return totalSolved;
    }

    public void setTotalSolved(Long totalSolved) {
        this.totalSolved = totalSolved;
    }

    @Override
    public String toString() {
        return "CategoryAnalyticsDTO{" +
                "category=" + category +
                ", totalAccess=" + totalAccess +
                ", totalSolved=" + totalSolved +
                '}';
    }
}

package com.example.demo.dto;

import com.example.demo.enums.GameCategory;

public interface CategoryCountSummary {
    GameCategory getCategory();
    Long getTotalAccessCount();
    Long getTotalSolveCount();
}

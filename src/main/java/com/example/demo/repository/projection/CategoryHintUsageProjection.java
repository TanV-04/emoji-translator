package com.example.demo.repository.projection;

import com.example.demo.enums.GameCategory;

public interface CategoryHintUsageProjection {
  GameCategory getCategory();

  Long getTotalHints();
}

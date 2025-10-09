package com.example.demo.repository.projection;

import com.example.demo.enums.GameCategory;

public interface CategoryAnalyticsProjection {

    GameCategory getCategory();

    Long getTotalAccess();

    Long getTotalSolved();

}

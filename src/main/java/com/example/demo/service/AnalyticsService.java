package com.example.demo.service;

import com.example.demo.dto.CategoryAnalyticsDTO;
import com.example.demo.dto.CategoryHintUsageDTO;
import com.example.demo.dto.EmojiAnalyticsDTO;
import com.example.demo.entity.EmojiMapping;
import com.example.demo.repository.EmojiMappingRepository;
import com.example.demo.repository.projection.CategoryAnalyticsProjection;
import com.example.demo.repository.projection.CategoryHintUsageProjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    @Autowired
    private EmojiMappingRepository emojiMappingRepository;

    // Top 10 most accessed emojis
    public List<EmojiAnalyticsDTO> getTopAccessedEmojis() {
        List<EmojiMapping> emojis = emojiMappingRepository.findTop10ByOrderByAccessCountDesc();
        return emojis.stream()
            .map(e -> new EmojiAnalyticsDTO(e.getEmojis(), e.getAccessCount()))
            .collect(Collectors.toList());
    }

    // Aggregated total access and solve counts per category
    public List<CategoryAnalyticsDTO> getCategoryAnalytics() {
        List<CategoryAnalyticsProjection> results = emojiMappingRepository.findCategoryAccessAndSolveCounts();
        return results.stream()
            .map(r -> new CategoryAnalyticsDTO(r.getCategory(), r.getTotalAccess(), r.getTotalSolved()))
            .collect(Collectors.toList());
    }

    // Aggregated total hint usage per category
    public List<CategoryHintUsageDTO> getHintUsageAnalytics() {
        List<CategoryHintUsageProjection> results = emojiMappingRepository.findHintUsageByCategory();
        return results.stream()
            .map(r -> new CategoryHintUsageDTO(r.getCategory(), r.getTotalHints()))
            .collect(Collectors.toList());
    }
}
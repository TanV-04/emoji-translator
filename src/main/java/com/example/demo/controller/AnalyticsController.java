package com.example.demo.controller;

import com.example.demo.dto.CategoryAnalyticsDTO;
import com.example.demo.dto.EmojiAnalyticsDTO;
import com.example.demo.entity.EmojiMapping;
import com.example.demo.service.AnalyticsService;
import com.example.demo.service.EmojiMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private EmojiMappingService emojiMappingService;

    @GetMapping("/top-emojis")
    public List<EmojiAnalyticsDTO> getTopAccessedEmojis(@RequestParam(defaultValue = "10") int limit) {
        List<EmojiMapping> emojis = emojiMappingService.getTopAccessedEmojis(limit);
        return emojis.stream()
                .map(e -> new EmojiAnalyticsDTO(e.getEmojis(), e.getAccessCount()))
                .collect(Collectors.toList());
    }

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/categories")
    public List<CategoryAnalyticsDTO> getCategoryAnalytics() {
        return analyticsService.getCategoryAnalytics();
    }
}

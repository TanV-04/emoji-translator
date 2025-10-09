package com.example.demo.service;

import com.example.demo.entity.EmojiMapping;
import com.example.demo.enums.GameCategory;
import com.example.demo.repository.EmojiMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmojiMappingService {

    @Autowired
    private EmojiMappingRepository emojiMappingRepository;

    public List<EmojiMapping> getAllMappings() {
        return emojiMappingRepository.findAll();
    }

    public List<EmojiMapping> getMappingsByCategory(GameCategory category) {
        return emojiMappingRepository.findByCategoryAndActiveTrue(category);
    }

    public List<EmojiMapping> getRandomQuestions(GameCategory category, int limit) {
        List<EmojiMapping> allRandom = emojiMappingRepository.findRandomByCategory(category.name());
        return allRandom.stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    public EmojiMapping getById(Long id) {
        return emojiMappingRepository.findById(id).orElse(null);
    }

    public EmojiMapping save(EmojiMapping emojiMapping) {
        return emojiMappingRepository.save(emojiMapping);
    }

    // Fetch top accessed emojis by limit
    public List<EmojiMapping> getTopAccessedEmojis(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return emojiMappingRepository.findAllByActiveTrueOrderByAccessCountDesc(pageable).getContent();
    }

    // New method to increment hint usage count
    public EmojiMapping incrementHintUsageCount(Long id) {
        EmojiMapping emojiMapping = getById(id);
        if (emojiMapping != null) {
            emojiMapping.incrementHintUsageCount();
            emojiMapping = save(emojiMapping);
        }
        return emojiMapping;
    }
}
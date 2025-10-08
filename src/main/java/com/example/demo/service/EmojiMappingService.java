package com.example.demo.service;

import com.example.demo.entity.EmojiMapping;
import com.example.demo.enums.GameCategory;
import com.example.demo.repository.EmojiMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        // Get all random questions and then limit in Java
        List<EmojiMapping> allRandom = emojiMappingRepository.findRandomByCategory(category.name());
        
        // Return only the requested number
        return allRandom.stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    public EmojiMapping getById(Long id) {
        return emojiMappingRepository.findById(id).orElse(null);
    }
}
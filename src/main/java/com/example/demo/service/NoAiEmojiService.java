package com.example.demo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
@Service
public class NoAiEmojiService {

    private Map<String, String> emojiDictionary;
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        try {
            // Load the emoji_mappings.json file from resources
            InputStream inputStream = new ClassPathResource("emoji_mappings.json").getInputStream();
            emojiDictionary = objectMapper.readValue(inputStream, new TypeReference<Map<String, String>>() {});
            System.out.println("Loaded emoji dictionary with " + emojiDictionary.size() + " entries.");
        } catch (IOException e) {
            System.err.println("Failed to load emoji_mappings.json: " + e.getMessage());
            emojiDictionary = new HashMap<>(); // Initialize an empty map to avoid NullPointer
        }
    }

    public String translateSentenceToEmojis(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            return "";
        }

        StringBuilder translatedSentence = new StringBuilder();
        // Convert sentence to lowercase for case-insensitive matching
        String lowerCaseSentence = sentence.toLowerCase();

        // Use a simple word-by-word replacement.
        // For more complex replacements (e.g., phrases), you'd need a different strategy.
        String[] words = lowerCaseSentence.split("\\b"); // Splits by word boundaries, keeping delimiters/punctuation separate

        for (String word : words) {
            // Clean the word for lookup (remove punctuation if it's attached)
            String cleanWord = word.replaceAll("[^a-zA-Z0-9]", ""); // Keep only alphanumeric for dictionary lookup

            if (emojiDictionary.containsKey(cleanWord)) {
                translatedSentence.append(emojiDictionary.get(cleanWord));
            } else {
                // If not found in dictionary, append the original word fragment (preserving spaces/punctuation)
                translatedSentence.append(word);
            }
        }

        return translatedSentence.toString().trim();
    }
}

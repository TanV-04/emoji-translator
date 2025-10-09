package com.example.demo.controller;

import com.example.demo.service.NoAiEmojiService;
import com.example.demo.service.FavoriteEmojiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/no-ai/emoji")
public class NoAiEmojiController {

    private final NoAiEmojiService noAiEmojiService;
    private final FavoriteEmojiService favoriteEmojiService;

    @Autowired
    public NoAiEmojiController(NoAiEmojiService noAiEmojiService, FavoriteEmojiService favoriteEmojiService) {
        this.noAiEmojiService = noAiEmojiService;
        this.favoriteEmojiService = favoriteEmojiService;
    }

    @GetMapping("/translate")
    public String translateSentence(@RequestParam String sentence) {
        // 🧠 1️⃣ Check if it matches a saved favorite name
        String savedEmoji = favoriteEmojiService.getEmojiByName(sentence);
        if (savedEmoji != null) {
            return savedEmoji;
        }

        // 🧠 2️⃣ Otherwise, translate text normally
        return noAiEmojiService.translateSentenceToEmojis(sentence);
    }
}

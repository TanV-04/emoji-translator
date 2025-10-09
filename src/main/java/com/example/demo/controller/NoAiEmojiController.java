package com.example.demo.controller;

import com.example.demo.service.NoAiEmojiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/no-ai/emoji")
public class NoAiEmojiController {

    private final NoAiEmojiService noAiEmojiService;

    @Autowired
    public NoAiEmojiController(NoAiEmojiService noAiEmojiService) {
        this.noAiEmojiService = noAiEmojiService;
    }

    @GetMapping("/translate")
    public String translateSentence(@RequestParam String sentence) {
        return noAiEmojiService.translateSentenceToEmojis(sentence);
    }
}

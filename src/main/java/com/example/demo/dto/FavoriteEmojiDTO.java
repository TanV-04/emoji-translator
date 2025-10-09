package com.example.demo.dto;

import lombok.Data;

@Data
public class FavoriteEmojiDTO {
    private Long id;
    private String name;
    private String text;
    private String emojiTranslation;
}


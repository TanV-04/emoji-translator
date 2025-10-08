package com.example.demo.dto;

public class EmojiAnalyticsDTO {
    private String emojis;
    private Long accessCount;

    public EmojiAnalyticsDTO(String emojis, Long accessCount) {
        this.emojis = emojis;
        this.accessCount = accessCount;
    }

    public String getEmojis() {
        return emojis;
    }

    public void setEmojis(String emojis) {
        this.emojis = emojis;
    }

    public Long getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(Long accessCount) {
        this.accessCount = accessCount;
    }
}

package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_attempts")
public class GameAttempt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonIgnore  // Prevent circular reference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_session_id", nullable = false)
    private GameSession gameSession;
    
    @JsonIgnore  // Don't expose full emoji mapping in attempts
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emoji_mapping_id", nullable = false)
    private EmojiMapping emojiMapping;
    
    @Column(name = "user_answer", length = 100)
    private String userAnswer;
    
    @Column(nullable = false)
    private Boolean correct = false;
    
    @Column(name = "points_earned")
    private Integer pointsEarned = 0;
    
    @Column(name = "hint_used")
    private Boolean hintUsed = false;
    
    @Column(name = "attempted_at", updatable = false)
    private LocalDateTime attemptedAt;
    
    @PrePersist
    protected void onCreate() {
        attemptedAt = LocalDateTime.now();
    }

    // Constructors
    public GameAttempt() {}

    public GameAttempt(GameSession gameSession, EmojiMapping emojiMapping, String userAnswer) {
        this.gameSession = gameSession;
        this.emojiMapping = emojiMapping;
        this.userAnswer = userAnswer;
        this.correct = false;
        this.pointsEarned = 0;
        this.hintUsed = false;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameSession getGameSession() {
        return gameSession;
    }

    public void setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
    }

    public EmojiMapping getEmojiMapping() {
        return emojiMapping;
    }

    public void setEmojiMapping(EmojiMapping emojiMapping) {
        this.emojiMapping = emojiMapping;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Integer getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(Integer pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public Boolean getHintUsed() {
        return hintUsed;
    }

    public void setHintUsed(Boolean hintUsed) {
        this.hintUsed = hintUsed;
    }

    public LocalDateTime getAttemptedAt() {
        return attemptedAt;
    }

    public void setAttemptedAt(LocalDateTime attemptedAt) {
        this.attemptedAt = attemptedAt;
    }
}
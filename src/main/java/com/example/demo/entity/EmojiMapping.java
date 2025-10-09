package com.example.demo.entity;

import com.example.demo.enums.DifficultyLevel;
import com.example.demo.enums.GameCategory;
import jakarta.persistence.*;

@Entity
@Table(name = "emoji_mappings")
public class EmojiMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String emojis;

    @Column(nullable = false, length = 100)
    private String answer;

    @Column(length = 500)
    private String hint;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private GameCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private DifficultyLevel difficulty = DifficultyLevel.MEDIUM;

    @Column(nullable = false)
    private Integer points = 10;

    @Column(name = "alternative_answers", length = 500)
    private String alternativeAnswers;

    @Column(nullable = false)
    private Boolean active = true;

    // Track how many times this emoji has been accessed
    @Column(nullable = false)
    private Long accessCount = 0L;

    // Track how many times this emoji has been solved correctly
    @Column(nullable = false)
    private Long solveCount = 0L;

    // Track how many times hints were requested for this emoji
    @Column(nullable = false)
    private Long hintUsageCount = 0L;

    // Constructors
    public EmojiMapping() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmojis() {
        return emojis;
    }

    public void setEmojis(String emojis) {
        this.emojis = emojis;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public GameCategory getCategory() {
        return category;
    }

    public void setCategory(GameCategory category) {
        this.category = category;
    }

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getAlternativeAnswers() {
        return alternativeAnswers;
    }

    public void setAlternativeAnswers(String alternativeAnswers) {
        this.alternativeAnswers = alternativeAnswers;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(Long accessCount) {
        this.accessCount = accessCount;
    }

    public void incrementAccessCount() {
        this.accessCount++;
    }

    public Long getSolveCount() {
        return solveCount;
    }

    public void setSolveCount(Long solveCount) {
        this.solveCount = solveCount;
    }

    public void incrementSolveCount() {
        this.solveCount++;
    }

    public Long getHintUsageCount() {
        return hintUsageCount;
    }

    public void setHintUsageCount(Long hintUsageCount) {
        this.hintUsageCount = hintUsageCount;
    }

    public void incrementHintUsageCount() {
        this.hintUsageCount++;
    }
}

package com.example.demo.dto;

public class GameQuestionResponse {
    private Long sessionId;
    private Long questionId;
    private String emojis;
    private String category;
    private Integer questionNumber;
    private Integer totalQuestions;
    private Integer points;
    private String hint;
    private Boolean hintAvailable;

    public GameQuestionResponse() {}

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getEmojis() {
        return emojis;
    }

    public void setEmojis(String emojis) {
        this.emojis = emojis;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public Boolean getHintAvailable() {
        return hintAvailable;
    }

    public void setHintAvailable(Boolean hintAvailable) {
        this.hintAvailable = hintAvailable;
    }
}

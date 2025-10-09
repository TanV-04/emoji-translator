package com.example.demo.controller;

import com.example.demo.dto.GameAnswerRequest;
import com.example.demo.dto.GameQuestionResponse;
import com.example.demo.dto.GameResultResponse;
import com.example.demo.enums.GameCategory;
import com.example.demo.service.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/start")
    public ResponseEntity<GameQuestionResponse> startGame(@RequestParam GameCategory category) {
        GameQuestionResponse response = gameService.startNewGame(category);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/next-question")
    public ResponseEntity<GameQuestionResponse> getNextQuestion(@RequestParam Long sessionId) {
        GameQuestionResponse response = gameService.getNextQuestion(sessionId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/submit-answer")
    public ResponseEntity<GameResultResponse> submitAnswer(@Valid @RequestBody GameAnswerRequest request) {
        GameResultResponse response = gameService.submitAnswer(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hint")
    public ResponseEntity<GameQuestionResponse> getHint(
            @RequestParam Long sessionId,
            @RequestParam Long questionId) {
        GameQuestionResponse response = gameService.getHintForQuestion(sessionId, questionId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history")
    public ResponseEntity<?> getGameHistory() {
        return ResponseEntity.ok(gameService.getUserGameHistory());
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(GameCategory.values());
    }
}
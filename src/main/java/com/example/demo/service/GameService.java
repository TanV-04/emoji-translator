package com.example.demo.service;

import com.example.demo.dto.GameAnswerRequest;
import com.example.demo.dto.GameQuestionResponse;
import com.example.demo.dto.GameResultResponse;
import com.example.demo.entity.EmojiMapping;
import com.example.demo.entity.GameAttempt;
import com.example.demo.entity.GameSession;
import com.example.demo.entity.User;
import com.example.demo.enums.GameCategory;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.GameAttemptRepository;
import com.example.demo.repository.GameSessionRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Autowired
    private GameAttemptRepository gameAttemptRepository;

    @Autowired
    private EmojiMappingService emojiMappingService;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    private static final int QUESTIONS_PER_GAME = 10;

    @Transactional
    public GameQuestionResponse startNewGame(GameCategory category) {
        User currentUser = authService.getCurrentUser();

        // Close any incomplete game for the user
        gameSessionRepository.findByUserAndCompletedFalse(currentUser)
                .ifPresent(session -> {
                    session.setCompleted(true);
                    session.setCompletedAt(LocalDateTime.now());
                    gameSessionRepository.save(session);
                });

        GameSession gameSession = new GameSession();
        gameSession.setUser(currentUser);
        gameSession.setCategory(category);
        gameSession.setTotalQuestions(0);
        gameSession.setCorrectAnswers(0);
        gameSession.setTotalScore(0);
        gameSession.setCompleted(false);
        gameSession.setStartedAt(LocalDateTime.now());

        gameSession = gameSessionRepository.save(gameSession);

        return getNextQuestion(gameSession.getId());
    }

    @Transactional
    public GameQuestionResponse getNextQuestion(Long sessionId) {
        GameSession session = gameSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Game session not found"));

        if (session.getCompleted()) {
            throw new IllegalStateException("Game already completed");
        }

        long attemptCount = gameAttemptRepository.countByGameSession(session);

        if (attemptCount >= QUESTIONS_PER_GAME) {
            completeGame(session);
            throw new IllegalStateException("Game completed. No more questions available.");
        }

        List<EmojiMapping> randomQuestions = emojiMappingService.getRandomQuestions(
                session.getCategory(), 1);

        if (randomQuestions.isEmpty()) {
            throw new ResourceNotFoundException("No questions available for this category");
        }

        EmojiMapping question = randomQuestions.get(0);

        // Increment the access count here
        question.incrementAccessCount();
        emojiMappingService.save(question); // save the updated entity

        GameQuestionResponse response = new GameQuestionResponse();
        response.setSessionId(session.getId());
        response.setQuestionId(question.getId());
        response.setEmojis(question.getEmojis());
        response.setCategory(session.getCategory().name());
        response.setQuestionNumber((int) attemptCount + 1);
        response.setTotalQuestions(QUESTIONS_PER_GAME);
        response.setPoints(question.getPoints());
        response.setHintAvailable(question.getHint() != null && !question.getHint().isEmpty());
        response.setHint(null);

        return response;
    }

    @Transactional
    public GameResultResponse submitAnswer(GameAnswerRequest request) {
        GameSession session = gameSessionRepository.findById(request.getSessionId())
                .orElseThrow(() -> new ResourceNotFoundException("Game session not found"));

        EmojiMapping question = emojiMappingService.getById(request.getQuestionId());
        if (question == null) {
            throw new ResourceNotFoundException("Question not found");
        }

        boolean isCorrect = checkAnswer(request.getAnswer(), question);

        GameAttempt attempt = new GameAttempt();
        attempt.setGameSession(session);
        attempt.setEmojiMapping(question);
        attempt.setUserAnswer(request.getAnswer());
        attempt.setCorrect(isCorrect);
        attempt.setHintUsed(request.getHintUsed() != null && request.getHintUsed());

        int pointsEarned = 0;
        if (isCorrect) {
            pointsEarned = question.getPoints();
            if (attempt.getHintUsed()) {
                pointsEarned = pointsEarned / 2;
            }
            session.setCorrectAnswers(session.getCorrectAnswers() + 1);

            // --- Increment solveCount here ---
            question.incrementSolveCount();
            emojiMappingService.save(question);
        }
        attempt.setPointsEarned(pointsEarned);

        gameAttemptRepository.save(attempt);

        session.setTotalQuestions(session.getTotalQuestions() + 1);
        session.setTotalScore(session.getTotalScore() + pointsEarned);
        gameSessionRepository.save(session);

        boolean gameCompleted = session.getTotalQuestions() >= QUESTIONS_PER_GAME;
        if (gameCompleted) {
            completeGame(session);
        }

        GameResultResponse response = new GameResultResponse();
        response.setCorrect(isCorrect);
        response.setCorrectAnswer(question.getAnswer());
        response.setUserAnswer(request.getAnswer());
        response.setPointsEarned(pointsEarned);
        response.setTotalScore(session.getTotalScore());
        response.setGameCompleted(gameCompleted);
        response.setCorrectAnswers(session.getCorrectAnswers());
        response.setTotalQuestions(session.getTotalQuestions());

        return response;
    }

    private boolean checkAnswer(String userAnswer, EmojiMapping question) {
        String normalizedUserAnswer = userAnswer.trim().toLowerCase();
        String normalizedCorrectAnswer = question.getAnswer().trim().toLowerCase();

        if (normalizedUserAnswer.equals(normalizedCorrectAnswer)) {
            return true;
        }

        if (question.getAlternativeAnswers() != null && !question.getAlternativeAnswers().isEmpty()) {
            String[] alternatives = question.getAlternativeAnswers().split(",");
            for (String alt : alternatives) {
                if (normalizedUserAnswer.equals(alt.trim().toLowerCase())) {
                    return true;
                }
            }
        }

        return false;
    }

    @Transactional
    public void completeGame(GameSession session) {
        session.setCompleted(true);
        session.setCompletedAt(LocalDateTime.now());
        gameSessionRepository.save(session);

        User user = session.getUser();
        user.setTotalScore(user.getTotalScore() + session.getTotalScore());
        user.setGamesPlayed(user.getGamesPlayed() + 1);
        userRepository.save(user);
    }

    @Transactional
    public GameQuestionResponse getHintForQuestion(Long sessionId, Long questionId) {
        GameSession session = gameSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Game session not found"));

        // Increment hint usage count when hint is requested
        EmojiMapping question = emojiMappingService.incrementHintUsageCount(questionId);
        if (question == null) {
            throw new ResourceNotFoundException("Question not found");
        }

        GameQuestionResponse response = new GameQuestionResponse();
        response.setSessionId(session.getId());
        response.setQuestionId(question.getId());
        response.setEmojis(question.getEmojis());
        response.setCategory(session.getCategory().name());
        response.setHint(question.getHint());
        response.setHintAvailable(true);

        return response;
    }

    public List<GameSession> getUserGameHistory() {
        User currentUser = authService.getCurrentUser();
        return gameSessionRepository.findByUserOrderByStartedAtDesc(currentUser);
    }
}

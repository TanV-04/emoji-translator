package com.example.demo.repository;

import com.example.demo.entity.GameAttempt;
import com.example.demo.entity.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameAttemptRepository extends JpaRepository<GameAttempt, Long> {
    List<GameAttempt> findByGameSessionOrderByAttemptedAtAsc(GameSession gameSession);
    Long countByGameSession(GameSession gameSession);
}
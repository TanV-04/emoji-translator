package com.example.demo.repository;

import com.example.demo.entity.GameSession;
import com.example.demo.entity.User;
import com.example.demo.enums.GameCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameSessionRepository extends JpaRepository<GameSession, Long> {
    List<GameSession> findByUserOrderByStartedAtDesc(User user);
    List<GameSession> findByUserAndCategoryOrderByStartedAtDesc(User user, GameCategory category);
    Optional<GameSession> findByUserAndCompletedFalse(User user);
}

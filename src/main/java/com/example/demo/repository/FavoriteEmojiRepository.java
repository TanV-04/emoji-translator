package com.example.demo.repository;

import com.example.demo.entity.FavoriteEmoji;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteEmojiRepository extends JpaRepository<FavoriteEmoji, Long> {
    FavoriteEmoji findByName(String name);
}

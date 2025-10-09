package com.example.demo.service;

import com.example.demo.entity.FavoriteEmoji;
import com.example.demo.repository.FavoriteEmojiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteEmojiService {

    @Autowired
    private FavoriteEmojiRepository favoriteEmojiRepository;

    public FavoriteEmoji saveFavorite(String name, String text, String emojiTranslation) {
        FavoriteEmoji favorite = new FavoriteEmoji();
        favorite.setName(name);
        favorite.setText(text);
        favorite.setEmojiTranslation(emojiTranslation);
        return favoriteEmojiRepository.save(favorite);
    }

    public List<FavoriteEmoji> getFavorites() {
        return favoriteEmojiRepository.findAll();
    }

    public FavoriteEmoji updateFavoriteName(Long id, String newName) {
        FavoriteEmoji favorite = favoriteEmojiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Favorite not found"));
        favorite.setName(newName);
        return favoriteEmojiRepository.save(favorite);
    }

    public void deleteFavorite(Long id) {
        favoriteEmojiRepository.deleteById(id);
    }

    // 🧠 New helper: get emoji combo by name
    public String getEmojiByName(String name) {
        FavoriteEmoji fav = favoriteEmojiRepository.findByName(name);
        return fav != null ? fav.getEmojiTranslation() : null;
    }
}

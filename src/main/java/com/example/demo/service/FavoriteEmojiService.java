package com.example.demo.service;

import com.example.demo.entity.FavoriteEmoji;
import com.example.demo.entity.User;
import com.example.demo.repository.FavoriteEmojiRepository;
import com.example.demo.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteEmojiService {

    @Autowired
    private FavoriteEmojiRepository favoriteEmojiRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public FavoriteEmoji saveFavorite(String name, String text, String emojiTranslation) {
        User currentUser = userDetailsService.getCurrentUser();
        FavoriteEmoji favorite = FavoriteEmoji.builder()
                .name(name)
                .text(text)
                .emojiTranslation(emojiTranslation)
                .user(currentUser)
                .build();
        return favoriteEmojiRepository.save(favorite);
    }

    public List<FavoriteEmoji> getFavorites() {
        User currentUser = userDetailsService.getCurrentUser();
        return favoriteEmojiRepository.findByUser(currentUser);
    }

    public FavoriteEmoji updateFavoriteName(Long id, String newName) {
        FavoriteEmoji fav = favoriteEmojiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Favorite not found"));
        fav.setName(newName);
        return favoriteEmojiRepository.save(fav);
    }

    public void deleteFavorite(Long id) {
        favoriteEmojiRepository.deleteById(id);
    }
}

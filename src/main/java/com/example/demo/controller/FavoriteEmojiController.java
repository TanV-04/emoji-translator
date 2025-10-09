package com.example.demo.controller;

import com.example.demo.dto.FavoriteEmojiDTO;
import com.example.demo.entity.FavoriteEmoji;
import com.example.demo.service.FavoriteEmojiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteEmojiController {

    @Autowired
    private FavoriteEmojiService favoriteEmojiService;

    @PostMapping("/save")
    public ResponseEntity<FavoriteEmoji> saveFavorite(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String text = request.get("text");
        String emojiTranslation = request.get("emojiTranslation");
        return ResponseEntity.ok(favoriteEmojiService.saveFavorite(name, text, emojiTranslation));
    }

    @GetMapping
    public ResponseEntity<List<FavoriteEmoji>> getFavorites() {
        return ResponseEntity.ok(favoriteEmojiService.getFavorites());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FavoriteEmojiDTO> updateFavoriteName(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String newName = request.get("newName");
        FavoriteEmoji updated = favoriteEmojiService.updateFavoriteName(id, newName);

        FavoriteEmojiDTO dto = new FavoriteEmojiDTO();
        dto.setId(updated.getId());
        dto.setName(updated.getName());
        dto.setText(updated.getText());
        dto.setEmojiTranslation(updated.getEmojiTranslation());

        return ResponseEntity.ok(dto);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavorite(@PathVariable Long id) {
        favoriteEmojiService.deleteFavorite(id);
        return ResponseEntity.ok(Map.of("message", "Favorite deleted successfully"));
    }
}


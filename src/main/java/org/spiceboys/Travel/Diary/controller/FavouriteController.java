package org.spiceboys.Travel.Diary.controller;

import org.spiceboys.Travel.Diary.dto.FavouriteDTO;
import org.spiceboys.Travel.Diary.model.Favourite;
import org.spiceboys.Travel.Diary.service.FavouriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FavouriteController {
    private final FavouriteService favouriteService;

    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @GetMapping("/api/users/userId/{userId}/favourites")
    public ResponseEntity<List<FavouriteDTO>> getFavouritesByUserId(@PathVariable Long userId) {
        List<FavouriteDTO> favourites = favouriteService.getFavouritesByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(favourites);
    }

    @GetMapping("/api/users/username/{username}/favourites")
    public ResponseEntity<List<FavouriteDTO>> getFavouritesByUsername(@PathVariable String username) {
        List<FavouriteDTO> favourites = favouriteService.getFavouritesByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(favourites);
    }

    @PostMapping("/api/favourites")
    public ResponseEntity<FavouriteDTO> saveFavourite(@RequestBody Favourite favourite) {
        FavouriteDTO savedFavourite = favouriteService.saveFavourite(favourite);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFavourite);
    }

    @DeleteMapping("/api/favourites/{favouriteId}")
    public ResponseEntity<String> deleteActivity(@PathVariable Long favouriteId) {
        favouriteService.deleteFavouriteByFavouriteId(favouriteId);
        return ResponseEntity.status(HttpStatus.OK).body("Favourite deleted successfully");
    }

}

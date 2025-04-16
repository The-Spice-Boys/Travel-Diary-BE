package org.spiceboys.Travel.Diary.controller;

import org.spiceboys.Travel.Diary.model.Favourite;
import org.spiceboys.Travel.Diary.service.FavouriteService;
import org.spiceboys.Travel.Diary.service.UserService;
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

    @GetMapping("/api/users/username/{username}/favourites")
    public ResponseEntity<List<Favourite>> getFavouritesByUsername(@PathVariable String username) {
        List<Favourite> favourites = favouriteService.getFavouritesByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(favourites);
    }

    @PostMapping("/api/favourites")
    public ResponseEntity<Favourite> saveFavourite(@RequestBody Favourite favourite) {
        Favourite savedFavourite = favouriteService.saveFavourite(favourite);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFavourite);
    }
}

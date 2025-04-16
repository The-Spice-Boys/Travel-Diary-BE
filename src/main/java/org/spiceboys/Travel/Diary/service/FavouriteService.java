package org.spiceboys.Travel.Diary.service;

import org.spiceboys.Travel.Diary.model.Favourite;
import org.spiceboys.Travel.Diary.model.User;
import org.spiceboys.Travel.Diary.repository.FavouriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteService {
    private final FavouriteRepository favouriteRepository;

    public FavouriteService(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    public Favourite saveFavourite(Favourite favourite) {
        return favouriteRepository.save(favourite);
    }
}

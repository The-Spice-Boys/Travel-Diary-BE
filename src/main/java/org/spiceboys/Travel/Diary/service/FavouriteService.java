package org.spiceboys.Travel.Diary.service;

import org.spiceboys.Travel.Diary.exception.ContentNotFoundException;
import org.spiceboys.Travel.Diary.model.Favourite;
import org.spiceboys.Travel.Diary.model.User;
import org.spiceboys.Travel.Diary.repository.FavouriteRepository;
import org.spiceboys.Travel.Diary.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavouriteService {
    private final FavouriteRepository favouriteRepository;
    private final UserRepository userRepository;

    public FavouriteService(FavouriteRepository favouriteRepository, UserRepository userRepository) {
        this.favouriteRepository = favouriteRepository;
        this.userRepository = userRepository;
    }

    public Favourite saveFavourite(Favourite favourite) {
        return favouriteRepository.save(favourite);
    }

    public List<Favourite> getFavouritesByUsername(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new ContentNotFoundException("User not found");
        }

        User user = optionalUser.get();
        return favouriteRepository.findByUser(user);
    }
}

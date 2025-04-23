package org.spiceboys.Travel.Diary.service;

import org.spiceboys.Travel.Diary.dto.FavouriteDTO;
import org.spiceboys.Travel.Diary.dto.ItineraryDTO;
import org.spiceboys.Travel.Diary.exception.ContentNotFoundException;
import org.spiceboys.Travel.Diary.model.Favourite;
import org.spiceboys.Travel.Diary.model.User;
import org.spiceboys.Travel.Diary.repository.FavouriteRepository;
import org.spiceboys.Travel.Diary.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FavouriteService {
    private final FavouriteRepository favouriteRepository;
    private final UserRepository userRepository;
    private final ItineraryService itineraryService;

    public FavouriteService(FavouriteRepository favouriteRepository, UserRepository userRepository, ItineraryService itineraryService) {
        this.favouriteRepository = favouriteRepository;
        this.userRepository = userRepository;
        this.itineraryService = itineraryService;
    }

    public List<FavouriteDTO> getFavouritesByUsername(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new ContentNotFoundException("User with username " + username + " not found"));
        List<Favourite> favourites = favouriteRepository.findByUser(user);
        return favourites.stream().map(this::createFavouriteDTO).toList();
    }

    public List<FavouriteDTO> getFavouritesByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ContentNotFoundException("User with id " + userId + " not found"));
        List<Favourite> favourites =  favouriteRepository.findByUser(user);
        return favourites.stream().map(this::createFavouriteDTO).toList();
    }

    public FavouriteDTO saveFavourite(Favourite favourite) {
        Favourite newFavourite = favouriteRepository.save(favourite);
        return createFavouriteDTO(newFavourite);
    }

    private FavouriteDTO createFavouriteDTO(Favourite favourite) {
        ItineraryDTO itineraryDTO = itineraryService.createItineraryDTO(favourite.getItinerary());
        return new FavouriteDTO(
                favourite.getFavouriteId(),
                favourite.getUser().getUserId(),
                favourite.getUser().getUsername(),
                itineraryDTO
        );
    }

    public void deleteFavouriteByFavouriteId(Long favouriteId) {
        Favourite favourite = favouriteRepository.findById(favouriteId)
                .orElseThrow(() -> new ContentNotFoundException("Favourite with id " + favouriteId + " not found"));
        favouriteRepository.delete(favourite);
    }
}

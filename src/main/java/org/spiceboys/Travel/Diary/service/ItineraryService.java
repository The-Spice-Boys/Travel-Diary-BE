package org.spiceboys.Travel.Diary.service;

import org.spiceboys.Travel.Diary.exception.ContentNotFoundException;
import org.spiceboys.Travel.Diary.model.Country;
import org.spiceboys.Travel.Diary.model.Itinerary;
import org.spiceboys.Travel.Diary.model.User;
import org.spiceboys.Travel.Diary.repository.CountryRepository;
import org.spiceboys.Travel.Diary.repository.ItineraryRepository;
import org.spiceboys.Travel.Diary.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItineraryService {
    private final ItineraryRepository itineraryRepository;
    private final UserRepository userRepository;
    private final CountryRepository countryRepository;

    public ItineraryService(ItineraryRepository itineraryRepository, UserRepository userRepository, CountryRepository countryRepository) {
        this.itineraryRepository = itineraryRepository;
        this.userRepository = userRepository;
        this.countryRepository = countryRepository;
    }

    public Itinerary createItinerary(Itinerary itinerary) {
        return itineraryRepository.save(itinerary);
    }

    public List<Itinerary> getItinerariesByUsername(String username) {
       Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new ContentNotFoundException("User not found");
        }
        User user = optionalUser.get();
       return itineraryRepository.findByUser(user);
    }

    public Itinerary getItineraryById(Long itineraryId) {
        Optional<Itinerary> optionalItinerary = itineraryRepository.findById(itineraryId);
        if (optionalItinerary.isEmpty()) {
            throw new ContentNotFoundException("Itinerary not found");
        }
        return optionalItinerary.get();
    }

    public List<Itinerary> getItinerariesByCountryName(String countryName) {
        Optional<Country> optionalCountry = countryRepository.findCountryByCountryName(countryName);
        if (optionalCountry.isEmpty()){
            throw new ContentNotFoundException("Country not found");
        }
        Country country = optionalCountry.get();
        return itineraryRepository.findByCountry(country);
    }

    public List<Itinerary> getAllItineraries() {
        return itineraryRepository.findAll();
    }

    public boolean deleteItineraryById(Long itineraryId) {
        Optional<Itinerary> optionalItinerary = itineraryRepository.findById(itineraryId);
        if (optionalItinerary.isPresent()) {
            itineraryRepository.deleteById(itineraryId);
            return true;
        } else {
            return false;
        }
    }
}

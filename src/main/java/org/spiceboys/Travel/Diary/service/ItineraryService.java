package org.spiceboys.Travel.Diary.service;

import org.spiceboys.Travel.Diary.dto.ItineraryDTO;
import org.spiceboys.Travel.Diary.dto.PublicItineraryDTO;
import org.spiceboys.Travel.Diary.exception.ContentNotFoundException;
import org.spiceboys.Travel.Diary.model.Country;
import org.spiceboys.Travel.Diary.model.Itinerary;
import org.spiceboys.Travel.Diary.model.User;
import org.spiceboys.Travel.Diary.repository.CountryRepository;
import org.spiceboys.Travel.Diary.repository.ItineraryRepository;
import org.spiceboys.Travel.Diary.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

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

    public ItineraryDTO createItinerary(Itinerary itinerary) {
        Itinerary createdItinerary = itineraryRepository.save(itinerary);
        return createItineraryDTO(createdItinerary);
    }

    public List<ItineraryDTO> getItinerariesByUsername(String username) {
       User user = userRepository.findUserByUsername(username)
               .orElseThrow(() -> new ContentNotFoundException("User with username " + username + " not found"));
       return createDTOList(itineraryRepository.findByUser(user));
    }

    public List<ItineraryDTO> getItinerariesByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ContentNotFoundException("User with ID " + userId + " not found"));
        return createDTOList(itineraryRepository.findByUser(user));
    }

    public List<ItineraryDTO> getItinerariesByCountryName(String countryName) {
        Country country = countryRepository.findCountryByCountryName(countryName)
                .orElseThrow(() -> new ContentNotFoundException("Country with name " + countryName + " not found"));
        return createDTOList( itineraryRepository.findByCountry(country));
    }

    public ItineraryDTO updateItinerary(Long id, Map<String, Object> updates) {
        Itinerary itinerary = itineraryRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("Itinerary with ID " + id + " not found"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "itineraryTitle":
                    itinerary.setItineraryTitle((String) value);
                    break;
                case "isPrivate":
                    itinerary.setIsPrivate((Boolean) value);
                    break;
                case "modifiedAt":
                    itinerary.setModifiedAt(Instant.parse((String) value));
                    break;
            }
        });

        Itinerary updatedItinerary = itineraryRepository.save(itinerary);
        return createItineraryDTO(updatedItinerary);
    }

    public void deleteItineraryById(Long itineraryId) {
        Itinerary itinerary = itineraryRepository.findById(itineraryId)
                .orElseThrow(() -> new ContentNotFoundException("Itinerary with ID " + itineraryId + " not found"));
        itineraryRepository.delete(itinerary);
    }

    private List<ItineraryDTO> createDTOList(List<Itinerary> itineraries) {
        return itineraries.stream().map(this::createItineraryDTO).toList();
    }

    public ItineraryDTO createItineraryDTO(Itinerary itinerary) {
        return new PublicItineraryDTO(
                itinerary.getItineraryId(),
                itinerary.getItineraryTitle(),
                itinerary.getCountry().getCountryName(),
                itinerary.getModifiedAt(),
                itinerary.getIsPrivate(),
                itinerary.getUser().getUserId(),
                itinerary.getUser().getUsername()
        );
    }
}

package org.spiceboys.Travel.Diary.controller;

import org.spiceboys.Travel.Diary.dto.ItineraryDTO;
import org.spiceboys.Travel.Diary.exception.ItineraryNotFoundException;
import org.spiceboys.Travel.Diary.model.Itinerary;
import org.spiceboys.Travel.Diary.service.ItineraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ItineraryController {
    private final ItineraryService itineraryService;

    public ItineraryController(ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    @GetMapping("/users/username/{username}/itineraries")
    public ResponseEntity<List<ItineraryDTO>> getItinerariesByUsername(@PathVariable String username) {
        List<ItineraryDTO> itineraries = itineraryService.getItinerariesByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(itineraries);
    }

    @GetMapping("/users/userId/{userId}/itineraries")
    public ResponseEntity<List<ItineraryDTO>> getItinerariesByUserId(@PathVariable Long userId) {
        List<ItineraryDTO> itineraries = itineraryService.getItinerariesByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(itineraries);
    }

    @GetMapping("/countries/{countryName}/itineraries")
    public ResponseEntity<List<ItineraryDTO>> getItinerariesByCountryName(@PathVariable String countryName) {
        List<ItineraryDTO> itineraries = itineraryService.getItinerariesByCountryName(countryName);
        return ResponseEntity.status(HttpStatus.OK).body(itineraries);
    }

    @PostMapping("/itineraries")
    public ResponseEntity<ItineraryDTO> createItinerary(@RequestBody Itinerary itinerary) {
        ItineraryDTO createdItinerary = itineraryService.createItinerary(itinerary);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItinerary);
    }

    @PatchMapping("/itineraries/{itineraryId}")
    public ResponseEntity<ItineraryDTO> updateItinerary(
            @PathVariable Long itineraryId,
            @RequestBody Map<String, Object> updatedFields) {
        ItineraryDTO updatedItinerary = itineraryService.updateItinerary(itineraryId, updatedFields);
        return ResponseEntity.status(HttpStatus.OK).body(updatedItinerary);
    }

    @DeleteMapping("/itineraries/{itineraryId}")
    public ResponseEntity<String> deleteItinerary(@PathVariable Long itineraryId) {
        itineraryService.deleteItineraryById(itineraryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Itinerary deleted successfully");
    }
}






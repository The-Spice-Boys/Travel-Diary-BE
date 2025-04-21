package org.spiceboys.Travel.Diary.controller;

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

    @PostMapping("/itineraries")
    public ResponseEntity<Itinerary> createItinerary(@RequestBody Itinerary itinerary) {
        Itinerary createdItinerary = itineraryService.createItinerary(itinerary);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItinerary);
    }

    @GetMapping("/itineraries")
    public ResponseEntity<List<Itinerary>> getAllItineraries() {
        List<Itinerary> allItineraries = itineraryService.getAllItineraries();
        return new ResponseEntity<>(allItineraries, HttpStatus.OK);
    }

    @GetMapping("/itineraries/{itinerayId}")
    public ResponseEntity<Itinerary> getItineraryById(@PathVariable Long itinerayId) {
        Itinerary itinerary = itineraryService.getItineraryById(itinerayId);
        if (itinerary != null) {
            return ResponseEntity.status(HttpStatus.OK).body(itinerary);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/users/{username}/itineraries")
    public ResponseEntity<List<Itinerary>> getItinerariesByUsername(@PathVariable String username) {
        List<Itinerary> itineraries = itineraryService.getItinerariesByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(itineraries);
    }

    @GetMapping("/countries/{countryName}/itineraries")
    public ResponseEntity<List<Itinerary>> getItinerariesByCountryName(@PathVariable String countryName) {
        List<Itinerary> itineraries = itineraryService.getItinerariesByCountryName(countryName);
        return ResponseEntity.status(HttpStatus.OK).body(itineraries);
    }

    @PatchMapping("/itineraries/{itineraryId}")
    public ResponseEntity<Itinerary> updateItinerary(
            @PathVariable Long itineraryId,
            @RequestBody Map<String, Object> updatedFields) {
        try {
            Itinerary updatedItinerary = itineraryService.updateItinerary(itineraryId, updatedFields);
            return ResponseEntity.status(HttpStatus.OK).body(updatedItinerary);
        } catch (ItineraryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/itineraries/{itineraryId}")
    public ResponseEntity<String> deleteItinerary(@PathVariable Long itineraryId) {
        boolean isItinerayDeleted = itineraryService.deleteItineraryById(itineraryId);
        if (isItinerayDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Itinerary deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Itinerary not found");
        }
    }
}






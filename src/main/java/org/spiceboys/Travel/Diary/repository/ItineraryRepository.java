package org.spiceboys.Travel.Diary.repository;

import org.spiceboys.Travel.Diary.model.Country;
import org.spiceboys.Travel.Diary.model.Itinerary;
import org.spiceboys.Travel.Diary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
    List<Itinerary> findByUser(User user);
    List<Itinerary> findByCountry(Country country);
}

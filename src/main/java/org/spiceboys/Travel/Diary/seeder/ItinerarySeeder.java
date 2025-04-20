package org.spiceboys.Travel.Diary.seeder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.spiceboys.Travel.Diary.exception.ContentNotFoundException;
import org.spiceboys.Travel.Diary.model.User;
import org.spiceboys.Travel.Diary.model.Country;
import org.spiceboys.Travel.Diary.model.Itinerary;
import org.spiceboys.Travel.Diary.repository.CountryRepository;
import org.spiceboys.Travel.Diary.repository.ItineraryRepository;
import org.spiceboys.Travel.Diary.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.List;
import java.io.InputStream;
import java.util.Optional;

@Component
@Order(3)
public class ItinerarySeeder implements CommandLineRunner {
    private final ItineraryRepository itineraryRepository;
    private final CountryRepository countryRepository;
    private final ObjectMapper objectMapper;

    public ItinerarySeeder(ItineraryRepository itineraryRepository, CountryRepository countryRepository, ObjectMapper objectMapper) {
        this.itineraryRepository = itineraryRepository;
        this.countryRepository = countryRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (itineraryRepository.count() == 0) {
            InputStream inputStream = getClass().getResourceAsStream("/data/itineraries.json");
            if (inputStream != null) {
                List<Itinerary> itineraries = objectMapper.readValue(inputStream, new TypeReference<List<Itinerary>>() {});
                for (Itinerary itinerary : itineraries) {
                    Integer countryId = itinerary.getCountry().getCountryId();
                    Optional<Country> country = countryRepository.findById(countryId);
                    if (country.isPresent()){
                        itinerary.setCountry(country.get());
                    }else{
                        throw new ContentNotFoundException("Country Id not found");
                    }
                }
                itineraryRepository.saveAll(itineraries);
                System.out.println("✅ Database seeded with " + itineraries.size() + " itineraries");
            }
            else {
                System.out.println("Could not find itineraries.json");
            }
        } else {
            System.out.println("ℹ Database already seeded");
        }
    }
}

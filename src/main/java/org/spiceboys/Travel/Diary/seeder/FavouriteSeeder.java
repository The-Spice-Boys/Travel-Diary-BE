package org.spiceboys.Travel.Diary.seeder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.spiceboys.Travel.Diary.model.Favourite;
import org.spiceboys.Travel.Diary.model.Itinerary;
import org.spiceboys.Travel.Diary.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.List;
import java.io.InputStream;
import java.util.Optional;

@Component
@Order(7)
public class FavouriteSeeder implements CommandLineRunner {

    private final FavouriteRepository favouriteRepository;

    private final UserRepository userRepository;

    private final ItineraryRepository itineraryRepository;

    private final ObjectMapper objectMapper;

    public FavouriteSeeder(FavouriteRepository favouriteRepository, UserRepository userRepository, ItineraryRepository itineraryRepository, ObjectMapper objectMapper) {
        this.favouriteRepository = favouriteRepository;
        this.userRepository = userRepository;
        this.itineraryRepository = itineraryRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception{
        if (favouriteRepository.count() == 0){
            InputStream inputStream = this.getClass().getResourceAsStream("/data/favourites.json");
            if (inputStream != null){
                List<Favourite> favourites = objectMapper.readValue(inputStream, new TypeReference<List<Favourite>>() {
                });
                for (Favourite favourite: favourites){
                    Long itineraryId = favourite.getItinerary().getItineraryId();
                    Optional<Itinerary> optionalItinerary = itineraryRepository.findById(itineraryId);
                    if (optionalItinerary.isPresent()){
                        favourite.setItinerary(optionalItinerary.get());
                    }else{
                        throw new RuntimeException("Itinerary not found");
                    }
                }
                favouriteRepository.saveAll(favourites);
                System.out.println("✅ Database seeded with " + favourites.size() + " favourites");
            }else{
                System.out.println("Could not find favourites.json");
            }
        }else {
            System.out.println("ℹ Database already seeded with favourites");
        }
    }

}

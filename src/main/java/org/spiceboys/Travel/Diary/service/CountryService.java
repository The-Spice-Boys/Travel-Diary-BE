package org.spiceboys.Travel.Diary.service;

import org.spiceboys.Travel.Diary.exception.ContentNotFoundException;
import org.spiceboys.Travel.Diary.model.Country;
import org.spiceboys.Travel.Diary.repository.CountryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    public Country createCountry(Country country){
        return countryRepository.save(country);
    }

    public Country getCountryByCountryName(String countryName){
        Optional<Country> countryOptional = countryRepository.findCountryByCountryName(countryName);
        if (countryOptional.isPresent()) {
            return countryOptional.get();
        } else {
            throw new ContentNotFoundException("Country does not exist");
        }
    }

    public List<Country> getAllCountries(String countryName, String description, String countryPicUrl) {
        if (countryName != null && description != null && countryPicUrl != null) {
            return countryRepository.findByCountryNameContainingAndDescriptionContainingAndCountryPicUrlContaining(
                    countryName, description, countryPicUrl);
        } else if (countryName != null && description != null) {
            return countryRepository.findByCountryNameContainingAndDescriptionContaining(countryName, description);
        } else if (countryName != null && countryPicUrl != null) {
            return countryRepository.findByCountryNameContainingAndCountryPicUrlContaining(countryName, countryPicUrl);
        } else if (description != null && countryPicUrl != null) {
            return countryRepository.findByDescriptionContainingAndCountryPicUrlContaining(description, countryPicUrl);
        } else if (countryName != null) {
            return countryRepository.findByCountryNameContaining(countryName);
        } else if (description != null) {
            return countryRepository.findByDescriptionContaining(description);
        } else if (countryPicUrl != null) {
            return countryRepository.findByCountryPicUrlContaining(countryPicUrl);
        } else {
            return countryRepository.findAll();  // Return all countries if no parameters are provided
        }
    }
}

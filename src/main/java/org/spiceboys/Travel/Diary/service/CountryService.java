package org.spiceboys.Travel.Diary.service;

import org.spiceboys.Travel.Diary.exception.ContentNotFoundException;
import org.spiceboys.Travel.Diary.model.Country;
import org.spiceboys.Travel.Diary.repository.CountryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryByCountryName(String countryName){
        return countryRepository.findCountryByCountryName(countryName)
                .orElseThrow(() -> new ContentNotFoundException("Country with name " + countryName + " not found"));
    }

    public Country createCountry(Country country){
        return countryRepository.save(country);
    }
}

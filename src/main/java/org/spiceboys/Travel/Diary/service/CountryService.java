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
        if (countryOptional.isPresent()){
            return countryOptional.get();
        }else{
      throw new ContentNotFoundException("Country does not exist");
        }
    }
}

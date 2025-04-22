package org.spiceboys.Travel.Diary.controller;

import org.spiceboys.Travel.Diary.service.CountryService;
import org.spiceboys.Travel.Diary.model.Country;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService){
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        return ResponseEntity.status(HttpStatus.OK).body(countries);
    }

    @GetMapping("/countries/{countryName}")
    public ResponseEntity<Country> fetchCountryByCountryName(@PathVariable String countryName){
        Country fetchedCountry = countryService.getCountryByCountryName(countryName);
        return ResponseEntity.status(HttpStatus.OK).body(fetchedCountry);
    }

    @PostMapping("/countries")
    public ResponseEntity<Country> createCountry(@RequestBody Country country){
        Country createdCountry = countryService.createCountry(country);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCountry);
    }
}

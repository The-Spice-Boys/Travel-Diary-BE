package org.spiceboys.Travel.Diary.controller;

import org.spiceboys.Travel.Diary.service.CountryService;
import org.spiceboys.Travel.Diary.model.Country;
import org.spiceboys.Travel.Diary.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@RestController
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService){
        this.countryService = countryService;
    }

    @PostMapping("/countries")
    public ResponseEntity<Country> createCountry(
            @RequestBody Country country
    ){
        Country createdCountry = countryService.createCountry(country);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCountry);
    }

    @GetMapping("/countries/{countryName}")
    public ResponseEntity<Country> fetchCountryByCountryName(
            @PathVariable String countryName
    ){
        Country fetchedCountry = countryService.getCountryByCountryName(countryName);
        return ResponseEntity.status(HttpStatus.OK).body(fetchedCountry);
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getAllCountries(
            @RequestParam(required = false) String countryName,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String countryPicUrl) {

        List<Country> countries = countryService.getAllCountries(countryName, description, countryPicUrl);

        if (countries.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(countries);
        }
    }
}

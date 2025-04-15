package org.spiceboys.Travel.Diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.spiceboys.Travel.Diary.model.Country;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Optional<Country> findCountryByCountryName(String countryName);
}

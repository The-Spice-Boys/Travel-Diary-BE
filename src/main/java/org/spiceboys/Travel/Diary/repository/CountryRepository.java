package org.spiceboys.Travel.Diary.repository;

import org.spiceboys.Travel.Diary.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Optional<Country> findCountryByCountryName(String countryName);

}

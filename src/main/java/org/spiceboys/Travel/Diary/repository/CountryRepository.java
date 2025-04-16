package org.spiceboys.Travel.Diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.spiceboys.Travel.Diary.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Optional<Country> findCountryByCountryName(String countryName);

    List<Country> findByCountryNameContaining(String countryName);

    List<Country> findByDescriptionContaining(String description);

    List<Country> findByCountryPicUrlContaining(String countryPicUrl);

    List<Country> findByCountryNameContainingAndDescriptionContaining(String countryName, String description);

    List<Country> findByCountryNameContainingAndCountryPicUrlContaining(String countryName, String countryPicUrl);

    List<Country> findByDescriptionContainingAndCountryPicUrlContaining(String description, String countryPicUrl);

    List<Country> findByCountryNameContainingAndDescriptionContainingAndCountryPicUrlContaining(
            String countryName, String description, String countryPicUrl);
}

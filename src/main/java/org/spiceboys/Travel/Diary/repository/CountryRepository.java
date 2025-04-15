package org.spiceboys.Travel.Diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.spiceboys.Travel.Diary.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}

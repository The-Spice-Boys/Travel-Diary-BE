package org.spiceboys.Travel.Diary.repository;

import org.spiceboys.Travel.Diary.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {

}

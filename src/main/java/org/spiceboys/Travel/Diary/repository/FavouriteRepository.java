package org.spiceboys.Travel.Diary.repository;

import org.spiceboys.Travel.Diary.model.Favourite;
import org.spiceboys.Travel.Diary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    List<Favourite> findByUser(User user);
}

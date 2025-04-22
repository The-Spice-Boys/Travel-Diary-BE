package org.spiceboys.Travel.Diary.repository;

import org.spiceboys.Travel.Diary.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.spiceboys.Travel.Diary.model.Photo;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByActivity(Activity activity);
}

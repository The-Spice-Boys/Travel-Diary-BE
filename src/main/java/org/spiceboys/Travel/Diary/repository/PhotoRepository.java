package org.spiceboys.Travel.Diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.spiceboys.Travel.Diary.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {}

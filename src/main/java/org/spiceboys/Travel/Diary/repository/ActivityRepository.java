package org.spiceboys.Travel.Diary.repository;

import org.spiceboys.Travel.Diary.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.spiceboys.Travel.Diary.model.Country;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

}

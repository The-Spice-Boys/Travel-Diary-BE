package org.spiceboys.Travel.Diary.repository;

import org.spiceboys.Travel.Diary.model.Activity;
import org.spiceboys.Travel.Diary.model.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByItinerary(Itinerary itinerary);

    Optional<Activity> findByActivityId(Long activityId);
}

package org.spiceboys.Travel.Diary.service;

import org.spiceboys.Travel.Diary.exception.ContentNotFoundException;
import org.spiceboys.Travel.Diary.model.Activity;
import org.spiceboys.Travel.Diary.model.Itinerary;
import org.spiceboys.Travel.Diary.repository.ActivityRepository;
import org.spiceboys.Travel.Diary.repository.ItineraryRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final ItineraryRepository itineraryRepository;

    public ActivityService(ActivityRepository activityRepository, ItineraryRepository itineraryRepository) {
        this.activityRepository = activityRepository;
        this.itineraryRepository = itineraryRepository;
    }

    public List<Activity> getActivitiesByItineraryId(Long itineraryId) {
        Itinerary itinerary = itineraryRepository.findById(itineraryId)
                .orElseThrow(() -> new ContentNotFoundException("Itinerary with ID " + itineraryId + " not found"));
        return activityRepository.findByItinerary(itinerary);
    }

    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity updateActivity(Long activityId, Map<String, Object> updates) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ContentNotFoundException("Activity with ID " + activityId + " not found"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "title":
                    activity.setTitle((String) value);
                    break;
                case "completionStatus":
                    activity.setCompletionStatus((Boolean) value);
                    break;
            }
        });
        return activityRepository.save(activity);
    }
  
    public void deleteActivityByActivityId(Long activityId) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ContentNotFoundException("Activity with ID " + activityId + " not found"));
        activityRepository.delete(activity);
    }
}

package org.spiceboys.Travel.Diary.service;

import org.spiceboys.Travel.Diary.exception.ContentNotFoundException;
import org.spiceboys.Travel.Diary.exception.ItineraryNotFoundException;
import org.spiceboys.Travel.Diary.exception.ResourceNotFoundException;
import org.spiceboys.Travel.Diary.model.Activity;
import org.spiceboys.Travel.Diary.model.Itinerary;
import org.spiceboys.Travel.Diary.repository.ActivityRepository;
import org.spiceboys.Travel.Diary.repository.ItineraryRepository;
import org.springframework.stereotype.Service;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final ItineraryRepository itineraryRepository;

    public ActivityService(ActivityRepository activityRepository, ItineraryRepository itineraryRepository) {
        this.activityRepository = activityRepository;
        this.itineraryRepository = itineraryRepository;
    }

    public List<Activity> getActivitiesByItineraryId(Long itineraryId) {
        Itinerary optionalItinerary = itineraryRepository.findById(itineraryId)
                .orElseThrow(() -> new ContentNotFoundException("Itinerary with ID " + itineraryId + " not found"));

        return activityRepository.findByItinerary(optionalItinerary);
    }

    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public boolean deleteActivityByActivityId(Long activityId) {
        Optional<Activity> optionalActivity = activityRepository.findById(activityId);
        if (optionalActivity.isPresent()) {
            activityRepository.deleteById(activityId);
            return true;
        } else {
            return false;
        }
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

    public Activity getActivityEntityById(Long activityId) {
        Optional<Activity> activity = activityRepository.findById(activityId);
        if (activity.isPresent()) {
            return activity.get();
        }
        else {
            throw new ContentNotFoundException("Activity not found");
        }
    }
}

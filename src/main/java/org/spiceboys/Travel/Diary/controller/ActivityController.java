package org.spiceboys.Travel.Diary.controller;

import org.spiceboys.Travel.Diary.model.Activity;
import org.spiceboys.Travel.Diary.service.ActivityService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/itineraries/{itineraryId}/activities")
    public ResponseEntity<List<Activity>> getActivitiesByItineraryId(@PathVariable Long itineraryId) {
        List<Activity> activities = activityService.getActivitiesByItineraryId(itineraryId);
        return ResponseEntity.status(HttpStatus.OK).body(activities);
    }

    @PostMapping("/activities")
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        Activity createdActivity = activityService.createActivity(activity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdActivity);
    }

    @PatchMapping("/activities/{activityId}")
    public ResponseEntity<Activity> updateActivity(
            @PathVariable Long activityId,
            @RequestBody Map<String, Object> updatedFields) {
        Activity updatedActivity = activityService.updateActivity(activityId, updatedFields);
        return ResponseEntity.status(HttpStatus.OK).body(updatedActivity);
    }

    @DeleteMapping("/activities/{activityId}")
    public ResponseEntity<String> deleteActivity(@PathVariable Long activityId) {
        activityService.deleteActivityByActivityId(activityId);
        return ResponseEntity.status(HttpStatus.OK).body("Activity deleted successfully");
    }
}

package org.spiceboys.Travel.Diary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer activityId;


    @ManyToOne
    @JoinColumn(name = "itinerary_id")
    @JsonBackReference(value="itinerary-activities")
    public  Itinerary itinerary;

    @NotBlank
    private String itineraryTitle;

    private Boolean completeStatus;

    @OneToMany(mappedBy = "activity")
    @JsonManagedReference(value="activity-notes")
    private List<Note> notes;

    @OneToMany(mappedBy = "activity")
    @JsonManagedReference(value="activity-photos")
    private List<Photo> photos;

    public Activity() {}

    public Activity(String itineraryTitle, Boolean completeStatus, Itinerary itinerary) {
        this.itineraryTitle = itineraryTitle;
        this.completeStatus = completeStatus;
        this.itinerary = itinerary;
        this.notes = new ArrayList<>();
        this.photos = new ArrayList<>();
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    public Integer getId() {
        return activityId;
    }

    public String getItineraryTitle() {
        return itineraryTitle;
    }

    public void setItineraryTitle(String itineraryTitle) {
        this.itineraryTitle = itineraryTitle;
    }

    public Boolean getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(Boolean completeStatus) {
        this.completeStatus = completeStatus;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}

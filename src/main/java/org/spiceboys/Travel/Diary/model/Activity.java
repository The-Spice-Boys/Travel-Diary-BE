package org.spiceboys.Travel.Diary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;

@Entity
@Table(name="activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer activityId;


    @ManyToOne
    @JoinColumn(
            name = "itineraryId"
    )
    @JsonBackReference
    public  Itinerary itinerary;

    @NotBlank
    private String title;

    private Boolean completeStatus;

    @OneToMany(
            mappedBy = "activities"
    )
    @JsonManagedReference
    private List<Note> notes;

    @OneToMany(
            mappedBy = "activities"
    )
    @JsonManagedReference
    private List<Photo> photos;

    public Activity() {}

    public Activity(String title, Boolean completeStatus, Itinerary itinerary) {
        this.title = title;
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
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

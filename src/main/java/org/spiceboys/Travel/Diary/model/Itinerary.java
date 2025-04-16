package org.spiceboys.Travel.Diary.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "itineraries")
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itineraryId;

    @NotBlank
    private String itineraryTitle;

    @NotNull
    private Boolean isPrivate;

    @NotBlank
    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "country_id"
    )
    private Country country;

    @OneToMany(
            mappedBy = "itinerary"
    )
    @JsonManagedReference
    private List<Activity> activities;


    public Itinerary() {
    }

    public Itinerary(String itineraryTitle, Boolean isPrivate, User user, Country country) {
        this.itineraryTitle = itineraryTitle;
        this.isPrivate = isPrivate;
        this.user = user;
        this.country = country;
        this.modifiedAt = LocalDateTime.now();
        this.activities = new ArrayList<>();
    }

    public Long getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(Long itineraryId) {
        this.itineraryId = itineraryId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean isPrivate) {
        isPrivate = this.isPrivate;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getItineraryTitle() {
        return itineraryTitle;
    }

    public void setItineraryTitle(String itineraryTitle) {
        this.itineraryTitle = itineraryTitle;
    }
}


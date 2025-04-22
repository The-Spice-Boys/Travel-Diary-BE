package org.spiceboys.Travel.Diary.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.ArrayList;
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

    @NotNull
    private Instant modifiedAt;

    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    @JsonBackReference(value="user-itineraries")
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "country_id"
    )
    @JsonBackReference(value="country-itineraries")
    private Country country;

    @OneToMany(
            mappedBy = "itinerary",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference(value="itinerary-activities")
    private List<Activity> activities;

    @OneToMany(
            mappedBy = "itinerary",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference(value="favourite-itinerary-ref")
    private List<Favourite> favourites;


    public Itinerary() {
    }

    public Itinerary(String itineraryTitle, Boolean isPrivate, User user, Country country, Instant modifiedAt) {
        this.itineraryTitle = itineraryTitle;
        this.isPrivate = isPrivate;
        this.user = user;
        this.country = country;
        this.modifiedAt = modifiedAt;
        this.activities = new ArrayList<>();
        this.favourites = new ArrayList<>();
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

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getItineraryTitle() {
        return itineraryTitle;
    }

    public void setItineraryTitle(String itineraryTitle) {
        this.itineraryTitle = itineraryTitle;
    }

    public List<Favourite> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Favourite> favourites) {
        this.favourites = favourites;
    }
}


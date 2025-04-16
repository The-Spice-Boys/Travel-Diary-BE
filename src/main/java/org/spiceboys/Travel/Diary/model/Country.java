package org.spiceboys.Travel.Diary.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer countryId;

    @Column(unique = true)
    private String countryName;

    @NotBlank
    private String description;

    @URL
    @NotBlank
    private String countryPicUrl;

    @OneToMany(mappedBy = "country")
    @JsonManagedReference(value="country-itineraries")
    private List<Itinerary> itineraries;

    public Country() {}

    public Country(String countryName, String description, String countryPicUrl) {
        this.countryName = countryName;
        this.description = description;
        this.countryPicUrl = countryPicUrl;
        this.itineraries = new ArrayList<>();
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountryPicUrl() {
        return countryPicUrl;
    }

    public void setCountryPicUrl(String countryPicUrl) {
        this.countryPicUrl = countryPicUrl;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }
}

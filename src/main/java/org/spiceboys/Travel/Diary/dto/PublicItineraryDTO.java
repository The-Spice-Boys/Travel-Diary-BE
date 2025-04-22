package org.spiceboys.Travel.Diary.dto;

import org.spiceboys.Travel.Diary.model.Activity;

import java.time.Instant;
import java.util.List;

public class PublicItineraryDTO extends ItineraryDTO {
    private String title;
    private String countryName;
    private Instant modifiedAt;

    public PublicItineraryDTO(Long id,
                              String title,
                              String countryName,
                              Instant modifiedAt,
                              Boolean isPrivate) {
        super(id, isPrivate);
        this.title = title;
        this.countryName = countryName;
        this.modifiedAt = modifiedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}

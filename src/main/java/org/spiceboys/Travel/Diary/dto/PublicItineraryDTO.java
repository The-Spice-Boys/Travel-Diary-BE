package org.spiceboys.Travel.Diary.dto;

import java.time.Instant;

public class PublicItineraryDTO extends ItineraryDTO {
    private String title;
    private String countryName;
    private Instant modifiedAt;
    private Long userId;
    private String username;

    public PublicItineraryDTO(Long id,
                              String title,
                              String countryName,
                              Instant modifiedAt,
                              Boolean isPrivate,
                              Long userId,
                              String username
                              ) {
        super(id, isPrivate);
        this.title = title;
        this.countryName = countryName;
        this.modifiedAt = modifiedAt;
        this.userId = userId;
        this.username = username;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

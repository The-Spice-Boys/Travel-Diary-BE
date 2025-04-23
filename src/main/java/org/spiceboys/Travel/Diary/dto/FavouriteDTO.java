package org.spiceboys.Travel.Diary.dto;

public class FavouriteDTO {
    private Long favouriteId;
    private Long userId;
    private String username;
    private ItineraryDTO itinerary;

    public FavouriteDTO(Long favouriteId, Long userId, String username, ItineraryDTO itinerary) {
        this.favouriteId = favouriteId;
        this.userId = userId;
        this.username = username;
        this.itinerary = itinerary;
    }

    public Long getFavouriteId() {
        return favouriteId;
    }

    public void setFavouriteId(Long favouriteId) {
        this.favouriteId = favouriteId;
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

    public ItineraryDTO getItinerary() {
        return itinerary;
    }

    public void setItinerary(ItineraryDTO itinerary) {
        this.itinerary = itinerary;
    }
}

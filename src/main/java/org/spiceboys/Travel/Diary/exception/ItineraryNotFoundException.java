package org.spiceboys.Travel.Diary.exception;

public class ItineraryNotFoundException extends RuntimeException {
    public ItineraryNotFoundException(String message) {
        super(message);
    }
}

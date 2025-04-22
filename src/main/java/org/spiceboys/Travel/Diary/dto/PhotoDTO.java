package org.spiceboys.Travel.Diary.dto;

import org.spiceboys.Travel.Diary.model.Photo;

import java.time.Instant;

public class PhotoDTO {
    private final Long photoId;
    private final Long activityId;
    private final String caption;
    private final String imgUrl;
    private final Instant createdAt;
    public PhotoDTO(Photo photo) {
        this.photoId = photo.getPhotoId();
        this.activityId = photo.getActivity().getActivityId();
        this.caption = photo.getCaption();
        this.createdAt = photo.getModifiedAt();
        this.imgUrl = photo.getImgUrl();
    }

    public Long getPhotoId() {
        return photoId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public String getCaption() {
        return caption;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}

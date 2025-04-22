package org.spiceboys.Travel.Diary.service;

import org.spiceboys.Travel.Diary.dto.PhotoDTO;
import org.spiceboys.Travel.Diary.exception.ContentNotFoundException;
import org.spiceboys.Travel.Diary.model.Activity;
import org.spiceboys.Travel.Diary.model.Photo;
import org.spiceboys.Travel.Diary.repository.ActivityRepository;
import org.spiceboys.Travel.Diary.repository.PhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final ActivityRepository activityRepository;
    private final CloudinaryService cloudinaryService;

    public PhotoService(PhotoRepository photoRepository, ActivityRepository activityRepository, CloudinaryService cloudinaryService) {
        this.photoRepository = photoRepository;
        this.activityRepository = activityRepository;
        this.cloudinaryService = cloudinaryService;
    }

    public List<Photo> getPhotosByActivityId(Long activityId){
        Optional<Activity> optionalActivity = activityRepository.findById(activityId);
        if (optionalActivity.isEmpty()) {
            throw new ContentNotFoundException("Activity not found");
        }
        Activity activity = optionalActivity.get();
        return photoRepository.findByActivity(activity);
    }

    public PhotoDTO updatePhoto(Long photoId, MultipartFile file, String caption) throws IOException {
        Optional<Photo> photoOptional = photoRepository.findById(photoId);
        if(photoOptional.isPresent()) {
            Photo currentPhoto = photoOptional.get();
            if (caption != null) {
                currentPhoto.setCaption(caption);
            }
            if (file != null && !file.isEmpty()) {
                cloudinaryService.deleteImage(currentPhoto.getImgUrl());
                String imgUrl = cloudinaryService.uploadImage(file);
                currentPhoto.setImgUrl(imgUrl);
            }
            currentPhoto.setModifiedAt(Instant.now());
            photoRepository.save(currentPhoto);
            return new PhotoDTO(currentPhoto);
        }
        else {
            throw new ContentNotFoundException("Photo not found");
        }
    }

    public PhotoDTO createPhoto(MultipartFile file,
                                String caption,
                                Long activityId) throws IOException {
        String photoUrl = cloudinaryService.uploadImage(file);
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ContentNotFoundException("Activity not found"));

        Photo photo = new Photo();
        photo.setCaption(caption);
        photo.setActivity(activity);
        photo.setModifiedAt(Instant.now());
        photo.setImgUrl(photoUrl);
        photoRepository.save(photo);
        return new PhotoDTO(photo);
    }

    public void deletePhotoById(Long photoId) throws IOException {
        Optional<Photo> photoOptional = photoRepository.findById(photoId);
        if (photoOptional.isPresent()) {
            Photo photo = photoOptional.get();
            cloudinaryService.deleteImage(photo.getImgUrl());
            photoRepository.delete(photo);
        } else {
            throw new ContentNotFoundException("Photo not found, could not delete photo.");
        }
    }

    public PhotoDTO getPhotoById(Long photoId) {
        Optional<Photo> photo = photoRepository.findById(photoId);
        if (photo.isPresent()) {
            return new PhotoDTO(photo.get());
        }
        else {
            throw new ContentNotFoundException("Photo not found");
        }
    }
}

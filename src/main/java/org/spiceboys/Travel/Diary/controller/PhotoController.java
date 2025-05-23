package org.spiceboys.Travel.Diary.controller;

import org.spiceboys.Travel.Diary.dto.PhotoDTO;
import org.spiceboys.Travel.Diary.model.Photo;
import org.spiceboys.Travel.Diary.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PhotoController {
    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/activity/{activityId}/photos")
    public ResponseEntity<List<Photo>> getPhotosByActivityId(@PathVariable Long activityId){
            List<Photo> photos = photoService.getPhotosByActivityId(activityId);
            return ResponseEntity.status(HttpStatus.OK).body(photos);
    }

    @PatchMapping("/photos/{photoId}")
    public ResponseEntity<Object> changePhoto(@PathVariable Long photoId,
                                              @RequestParam(value = "file", required = false) MultipartFile file,
                                              @RequestParam(value = "caption", required = false) String caption) throws IOException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(photoService.updatePhoto(photoId, file, caption));
    }

    @PostMapping("/photos")
    public ResponseEntity<PhotoDTO> uploadPhoto(@RequestParam(value = "caption", required = false) String caption,
                                              @RequestParam MultipartFile file,
                                              @RequestParam Long activityId) throws IOException {
        PhotoDTO photo = photoService.createPhoto(file, caption, activityId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(photo);
    }

    @DeleteMapping("/photos/{photoId}")
    public ResponseEntity<Object> deletePhoto(@PathVariable Long photoId) throws IOException {
        photoService.deletePhotoById(photoId);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","Photo successfully deleted"));
    }

    // made for testing purposes
    @GetMapping("/photos/{photoId}")
    public ResponseEntity<Object> getPhotoById(@PathVariable Long photoId) throws IOException {
        PhotoDTO photo = photoService.getPhotoById(photoId);
        return ResponseEntity.status(HttpStatus.OK).body(photo);
    }

}

package org.spiceboys.Travel.Diary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noteId;

    @ManyToOne
    @JoinColumn(name="activity_id")
    @JsonBackReference(value="activity-notes")
    private Activity activity;

    private String text;

    @NotBlank
    private LocalDateTime modifiedAt;

    public Note() {}

    public Note(Activity activity, String text) {
        this.activity = activity;
        this.text = text;
        this.modifiedAt = LocalDateTime.now();
    }

    public Long getNoteId() {
        return noteId;
    }

    public Activity getActivity() {
        return activity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}

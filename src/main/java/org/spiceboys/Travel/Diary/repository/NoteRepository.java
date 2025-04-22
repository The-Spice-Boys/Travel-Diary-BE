package org.spiceboys.Travel.Diary.repository;

import org.spiceboys.Travel.Diary.model.Activity;
import org.spiceboys.Travel.Diary.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> getAllByActivity(Activity activity);
}

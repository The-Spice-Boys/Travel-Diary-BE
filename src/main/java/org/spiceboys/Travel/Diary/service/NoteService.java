package org.spiceboys.Travel.Diary.service;

import org.spiceboys.Travel.Diary.exception.ContentNotFoundException;
import org.spiceboys.Travel.Diary.model.Activity;
import org.spiceboys.Travel.Diary.model.Note;
import org.spiceboys.Travel.Diary.repository.ActivityRepository;
import org.spiceboys.Travel.Diary.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final ActivityRepository activityRepository;

    public NoteService(NoteRepository noteRepository, ActivityRepository activityRepository) {
        this.noteRepository = noteRepository;
        this.activityRepository = activityRepository;
    }

    public List<Note> getNotesByActivityId(Long activityId) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ContentNotFoundException("Activity with ID " + activityId + " not found"));
        return noteRepository.getAllByActivity(activity);
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public Note updateNote(Long noteId, Note note) {
        Note originalNote = noteRepository.findById(noteId)
                .orElseThrow(() -> new ContentNotFoundException("Note with ID " + noteId + " not found"));
        originalNote.setText(note.getText());
        originalNote.setModifiedAt(note.getModifiedAt());
        return noteRepository.save(originalNote);
    }

    public void deleteNote(Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ContentNotFoundException("Note with ID " + noteId + " not found"));
        noteRepository.delete(note);
    }
}

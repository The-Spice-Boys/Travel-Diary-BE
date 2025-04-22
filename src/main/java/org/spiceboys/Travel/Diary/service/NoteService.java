package org.spiceboys.Travel.Diary.service;

import org.spiceboys.Travel.Diary.exception.ContentNotFoundException;
import org.spiceboys.Travel.Diary.model.Activity;
import org.spiceboys.Travel.Diary.model.Note;
import org.spiceboys.Travel.Diary.repository.ActivityRepository;
import org.spiceboys.Travel.Diary.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final ActivityRepository activityRepository;

    public NoteService(NoteRepository noteRepository, ActivityRepository activityRepository) {
        this.noteRepository = noteRepository;
        this.activityRepository = activityRepository;
    }

    public List<Note> getNotesByActivityId(Long activityId) {
        Optional<Activity> activityOptional = activityRepository.findById(activityId);
        Activity activity = checkActivityExists(activityOptional);
        return noteRepository.getAllByActivity(activity);
    }

    private Activity checkActivityExists(Optional<Activity> activity) {
        if (activity.isEmpty()) {
            throw new ContentNotFoundException("Activity does not exist");
        }
        return activity.get();
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public Note updateNote(Long noteId, Note note) {
        Optional<Note> noteOptional = noteRepository.findById(noteId);
        Note originalNote = checkNoteExists(noteOptional);
        originalNote.setText(note.getText());
        originalNote.setModifiedAt(note.getModifiedAt());
        return noteRepository.save(originalNote);
    }

    public void deleteNote(Long noteId) {
        Optional<Note> noteOptional = noteRepository.findById(noteId);
        Note note = checkNoteExists(noteOptional);
        noteRepository.delete(note);
    }

    public Note checkNoteExists(Optional<Note> optional) {
        if (optional.isEmpty()) {
            throw new ContentNotFoundException("Note does not exist");
        }
        return optional.get();
    }
}

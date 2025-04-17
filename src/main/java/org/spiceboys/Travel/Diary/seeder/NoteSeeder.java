package org.spiceboys.Travel.Diary.seeder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.spiceboys.Travel.Diary.exception.ContentNotFoundException;
import org.spiceboys.Travel.Diary.model.Activity;
import org.spiceboys.Travel.Diary.model.Note;
import org.spiceboys.Travel.Diary.repository.ActivityRepository;
import org.spiceboys.Travel.Diary.repository.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.List;
import java.io.InputStream;
import java.util.Optional;

@Component
@Order(4)
class NoteSeeder implements CommandLineRunner {
    private final NoteRepository noteRepository;

    private final ActivityRepository activityRepository;

    private final ObjectMapper objectMapper;

    public NoteSeeder(NoteRepository noteRepository, ActivityRepository activityRepository,ObjectMapper objectMapper) {
        this.noteRepository = noteRepository;
        this.activityRepository = activityRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (noteRepository.count() == 0) {
            InputStream resourceAsStream = this.getClass().getResourceAsStream("/data/notes.json");
            if (resourceAsStream != null) {
                List<Note> notes = objectMapper.readValue(resourceAsStream, new TypeReference<List<Note>>(){});
                for (Note note : notes) {
                    Long activityId = note.getActivity().getActivityId();
                    Optional<Activity> activity = activityRepository.findById(activityId);
                    if (activity.isPresent()) {
                        note.setActivity(activity.get());
                    } else {
                        throw new ContentNotFoundException("Notes not found");
                    }
                }
                noteRepository.saveAll(notes);
                System.out.println("✅ Database seeded with " + notes.size() + " notes");
            } else {
                System.out.println("ℹ Could not find notes.json");
            }
        } else {
            System.out.println("ℹ Database already seeded with notes");
        }
    }
}

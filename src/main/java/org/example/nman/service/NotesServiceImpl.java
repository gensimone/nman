package org.example.nman.service;

import org.example.nman.dto.CreateNoteRequest;
import org.example.nman.dto.Note;
import org.example.nman.exception.NoteNotFoundException;
import org.example.nman.model.NoteDocument;
import org.example.nman.repository.NotesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesServiceImpl implements NotesService {

    private final NotesRepository repository;

    public NotesServiceImpl(NotesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Note> getNotes() {
        return this.repository
                .findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public Note getNote(String id) {
        NoteDocument noteDocument = this.repository
                .findById(id)
                .orElseThrow(NoteNotFoundException::new);

        return toDto(noteDocument);
    }

    @Override
    public Note createNote(CreateNoteRequest createNoteRequest) {
        NoteDocument notedocument = new NoteDocument();
        notedocument.setContent(createNoteRequest.getContent());
        notedocument.setTitle(createNoteRequest.getTitle());

        return toDto(this.repository.save(notedocument));
    }

    @Override
    public void deleteNote(String id) {
        this.repository
                .findById(id)
                .orElseThrow(NoteNotFoundException::new);

        this.repository.deleteById(id);
    }

    @Override
    public Note updateNote(String id, CreateNoteRequest createNoteRequest) {
        NoteDocument noteDocument = this.repository
                .findById(id)
                .orElseThrow(NoteNotFoundException::new);

        if (createNoteRequest.getTitle() != null) {
            noteDocument.setTitle(createNoteRequest.getTitle());
        }

        if (createNoteRequest.getContent() != null) {
            noteDocument.setContent(createNoteRequest.getContent());
        }

        return toDto(this.repository.save(noteDocument));
    }

    private Note toDto(NoteDocument noteDocument) {
        Note note = new Note();
        note.setId(noteDocument.getId());
        note.setTitle(noteDocument.getTitle());
        note.setContent(noteDocument.getContent());

        return note;
    }
}

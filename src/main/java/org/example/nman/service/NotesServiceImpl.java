package org.example.nman.service;

import org.example.nman.dto.CreateNoteRequest;
import org.example.nman.dto.Note;
import org.example.nman.dto.UpdateNoteRequest;
import org.example.nman.exception.NoteNotFoundException;
import org.example.nman.model.NoteModel;
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
    public List<Note> findAll() {
        return this.repository
                .findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public Note getNote(String id) {
        NoteModel noteModel = this.repository
                .findById(id)
                .orElseThrow(NoteNotFoundException::new);

        return toDto(noteModel);
    }

    @Override
    public Note createNote(CreateNoteRequest createNoteRequest) {
        NoteModel noteModel = new NoteModel();
        noteModel.setContent(createNoteRequest.getContent());
        noteModel.setTitle(createNoteRequest.getTitle());

        return toDto(this.repository.save(noteModel));
    }

    @Override
    public void deleteNote(String id) {
        this.repository
                .findById(id)
                .orElseThrow(NoteNotFoundException::new);

        this.repository.deleteById(id);
    }

    public void deleteNotes() {
        this.repository.deleteAll();
    }

    @Override
    public Note updateNote(String id, UpdateNoteRequest updateNoteRequest) {
        NoteModel noteModel = this.repository
                .findById(id)
                .orElseThrow(NoteNotFoundException::new);

        if (updateNoteRequest.getTitle() != null) {
            noteModel.setTitle(updateNoteRequest.getTitle());
        }

        if (updateNoteRequest.getContent() != null) {
            noteModel.setContent(updateNoteRequest.getContent());
        }

        return toDto(this.repository.save(noteModel));
    }

    private Note toDto(NoteModel noteModel) {
        Note note = new Note();
        note.setId(noteModel.getId());
        note.setTitle(noteModel.getTitle());
        note.setContent(noteModel.getContent());

        return note;
    }
}

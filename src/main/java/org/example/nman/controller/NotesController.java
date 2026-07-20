package org.example.nman.controller;

import org.example.nman.api.NotesApi;
import org.example.nman.dto.CreateNoteRequest;
import org.example.nman.dto.GetNotes200Response;
import org.example.nman.dto.Note;
import org.example.nman.dto.UpdateNoteRequest;
import org.example.nman.service.NotesService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotesController implements NotesApi {

    private final NotesService service;

    public NotesController(NotesService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Note> createNote(CreateNoteRequest createNoteRequest) {
        Note note = this.service.createNote(createNoteRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(note);
    }

    @Override
    public ResponseEntity<Void> deleteNote(String id) {
        this.service.deleteNote(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    public ResponseEntity<Void> deleteNotes() {
        this.service.deleteNotes();

        return ResponseEntity
                .noContent()
                .build();
    }

    @Override
    public ResponseEntity<Note> getNote(String id) {
        Note note = this.service.getNote(id);

        return ResponseEntity.ok(note);
    }

    @Override
    public ResponseEntity<GetNotes200Response> getNotes() {
        GetNotes200Response response = new GetNotes200Response();

        return ResponseEntity.ok(
                response.notes(this.service.findAll()));
    }

    @Override
    public ResponseEntity<Note> updateNote(String id, UpdateNoteRequest updateNoteRequest) {
        Note note = this.service.updateNote(id, updateNoteRequest);

        return ResponseEntity.ok(note);
    }
}

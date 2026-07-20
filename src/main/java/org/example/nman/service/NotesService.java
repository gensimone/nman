package org.example.nman.service;

import org.example.nman.dto.CreateNoteRequest;
import org.example.nman.dto.Note;
import org.example.nman.dto.UpdateNoteRequest;

import java.util.List;

public interface NotesService {

    List<Note> findAll();

    Note getNote(String id);

    Note createNote(CreateNoteRequest createNoteRequest);

    void deleteNote(String id);

    void deleteNotes();

    Note updateNote(String id, UpdateNoteRequest updateNoteRequest);
}

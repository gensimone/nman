package org.example.nman.service;

import org.example.nman.dto.CreateNoteRequest;
import org.example.nman.dto.Note;

import java.util.List;

public interface NotesService {

    List<Note> getNotes();

    Note getNote(String id);

    Note createNote(CreateNoteRequest createNoteRequest);

    void deleteNote(String id);

    Note updateNote(String id, CreateNoteRequest createNoteRequest);
}

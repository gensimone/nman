package org.example.nman.repository;

import org.example.nman.model.NoteDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesRepository extends MongoRepository<NoteDocument, String> {
}

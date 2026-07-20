package org.example.nman.repository;

import org.example.nman.model.NoteModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesRepository extends MongoRepository<NoteModel, String> {
}

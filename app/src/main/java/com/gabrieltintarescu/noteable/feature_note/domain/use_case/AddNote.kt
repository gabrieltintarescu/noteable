package com.gabrieltintarescu.noteable.feature_note.domain.use_case

import com.gabrieltintarescu.noteable.feature_note.domain.model.InvalidNoteException
import com.gabrieltintarescu.noteable.feature_note.domain.model.Note
import com.gabrieltintarescu.noteable.feature_note.domain.repository.NoteRepository

/**
 * @project Noteable
 * @author Gabriel Tintarescu
 * @created 10/26/2022
 */
class AddNote(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if(note.title.isBlank()){
            throw InvalidNoteException("The title of the note cannot be empty.")
        }
        if(note.content.isBlank()){
            throw InvalidNoteException("The content of the note cannot be empty.")
        }
        repository.insertNote(note)
    }
}
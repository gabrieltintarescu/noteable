package com.gabrieltintarescu.noteable.feature_note.domain.use_case

import com.gabrieltintarescu.noteable.feature_note.domain.model.Note
import com.gabrieltintarescu.noteable.feature_note.domain.repository.NoteRepository

/**
 * @project Noteable
 * @author Gabriel Tintarescu
 * @created 10/26/2022
 */
class DeleteNote(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }
}
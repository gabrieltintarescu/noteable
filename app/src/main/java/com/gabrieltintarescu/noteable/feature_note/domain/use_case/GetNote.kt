package com.gabrieltintarescu.noteable.feature_note.domain.use_case

import com.gabrieltintarescu.noteable.feature_note.domain.model.Note
import com.gabrieltintarescu.noteable.feature_note.domain.repository.NoteRepository

/**
 * @project Noteable
 * @author Gabriel Tintarescu
 * @created 10/27/2022
 */
class GetNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}
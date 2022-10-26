package com.gabrieltintarescu.noteable.feature_note.domain.repository

import com.gabrieltintarescu.noteable.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * @project Noteable
 * @author Gabriel Tintarescu
 * @created 10/26/2022
 *
 * Definition of repository
 */
interface NoteRepository {
    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}
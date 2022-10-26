package com.gabrieltintarescu.noteable.feature_note.data.data_source

import androidx.room.*
import com.gabrieltintarescu.noteable.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * @project Noteable
 * @author Gabriel Tintarescu
 * @created 10/26/2022
 */
@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM NOTE WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}
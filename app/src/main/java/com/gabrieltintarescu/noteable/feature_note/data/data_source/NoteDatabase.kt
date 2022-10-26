package com.gabrieltintarescu.noteable.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gabrieltintarescu.noteable.feature_note.domain.model.Note

/**
 * @project Noteable
 * @author Gabriel Tintarescu
 * @created 10/26/2022
 */
@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {
    abstract val noteDao: NoteDao
}
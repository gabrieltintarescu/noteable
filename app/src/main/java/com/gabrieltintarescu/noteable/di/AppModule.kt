package com.gabrieltintarescu.noteable.di

import android.app.Application
import androidx.room.Room
import com.gabrieltintarescu.noteable.feature_note.data.data_source.NoteDao
import com.gabrieltintarescu.noteable.feature_note.data.data_source.NoteDatabase
import com.gabrieltintarescu.noteable.feature_note.data.repository.NoteRepositoryImpl
import com.gabrieltintarescu.noteable.feature_note.domain.repository.NoteRepository
import com.gabrieltintarescu.noteable.feature_note.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @project Noteable
 * @author Gabriel Tintarescu
 * @created 10/26/2022
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase{
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases{
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }
}
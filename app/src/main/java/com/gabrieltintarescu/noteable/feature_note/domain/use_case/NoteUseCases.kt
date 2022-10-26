package com.gabrieltintarescu.noteable.feature_note.domain.use_case

/**
 * @project Noteable
 * @author Gabriel Tintarescu
 * @created 10/26/2022
 */
data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote
)

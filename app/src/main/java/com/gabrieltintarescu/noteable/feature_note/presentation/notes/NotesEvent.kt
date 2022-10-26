package com.gabrieltintarescu.noteable.feature_note.presentation.notes

import com.gabrieltintarescu.noteable.feature_note.domain.model.Note
import com.gabrieltintarescu.noteable.feature_note.domain.util.NoteOrder

/**
 * @project Noteable
 * @author Gabriel Tintarescu
 * @created 10/26/2022
 */
sealed class NotesEvent{
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}

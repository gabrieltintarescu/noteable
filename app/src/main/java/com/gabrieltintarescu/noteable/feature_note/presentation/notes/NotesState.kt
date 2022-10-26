package com.gabrieltintarescu.noteable.feature_note.presentation.notes

import com.gabrieltintarescu.noteable.feature_note.domain.model.Note
import com.gabrieltintarescu.noteable.feature_note.domain.util.NoteOrder
import com.gabrieltintarescu.noteable.feature_note.domain.util.OrderType

/**
 * @project Noteable
 * @author Gabriel Tintarescu
 * @created 10/26/2022
 */
data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)

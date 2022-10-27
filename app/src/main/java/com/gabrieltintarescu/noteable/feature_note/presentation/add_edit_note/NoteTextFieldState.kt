package com.gabrieltintarescu.noteable.feature_note.presentation.add_edit_note

/**
 * @project Noteable
 * @author Gabriel Tintarescu
 * @created 10/27/2022
 */
data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
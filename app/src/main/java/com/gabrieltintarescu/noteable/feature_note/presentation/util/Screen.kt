package com.gabrieltintarescu.noteable.feature_note.presentation.util

/**
 * @project Noteable
 * @author Gabriel Tintarescu
 * @created 10/27/2022
 */
sealed class Screen(val route: String) {
    object NotesScreen: Screen("notes_screen")
    object AddEditNotesScreen: Screen("add_edit_notes_screen")
}
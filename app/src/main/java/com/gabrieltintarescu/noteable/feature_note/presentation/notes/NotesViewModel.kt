package com.gabrieltintarescu.noteable.feature_note.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrieltintarescu.noteable.feature_note.domain.model.Note
import com.gabrieltintarescu.noteable.feature_note.domain.use_case.NoteUseCases
import com.gabrieltintarescu.noteable.feature_note.domain.util.NoteOrder
import com.gabrieltintarescu.noteable.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @project Noteable
 * @author Gabriel Tintarescu
 * @created 10/26/2022
 */
@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(NotesState())
    val state = _state.asStateFlow()

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null;

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        //TODO break function in separate event functions
        when (event) {
            is NotesEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                getNotes(event.noteOrder)

            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.update {
                    it.copy(
                        isOrderSectionVisible = !state.value.isOrderSectionVisible
                    )
                }
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder).onEach {notes->
            _state.update {
                it.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
        }.launchIn(viewModelScope)
    }
}
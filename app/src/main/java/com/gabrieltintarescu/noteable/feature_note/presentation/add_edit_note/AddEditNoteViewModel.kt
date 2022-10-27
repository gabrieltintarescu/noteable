package com.gabrieltintarescu.noteable.feature_note.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gabrieltintarescu.noteable.feature_note.domain.model.InvalidNoteException
import com.gabrieltintarescu.noteable.feature_note.domain.model.Note
import com.gabrieltintarescu.noteable.feature_note.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @project Noteable
 * @author Gabriel Tintarescu
 * @created 10/27/2022
 */
@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    // Mutable private state flow version
    private val _noteTitle = MutableStateFlow(
        NoteTextFieldState(
            hint = "Enter title..."
        )
    )

    // Immutable public state flow version
    val noteTitle = _noteTitle.asStateFlow()

    private val _noteContent = MutableStateFlow(
        NoteTextFieldState(
            hint = "Enter some content"
        )
    )
    val noteContent = _noteContent.asStateFlow()

    private val _noteColor = mutableStateOf<Int>(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let {noteId->
            if(noteId != -1){
                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.also {note->
                        currentNoteId = note.id
                        _noteTitle.update { it.copy(
                            text = note.title,
                            isHintVisible = false
                        ) }
                        _noteContent.update { it.copy(
                             text = note.content,
                            isHintVisible = false
                        ) }
                        _noteColor.value = note.color
                    }
                }
            }
        }
    }

    //TODO Break into separate functions with each action
    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.update {
                    it.copy(
                        text = event.value
                    )
                }
            }
            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.update {
                    it.copy(
                        isHintVisible = !event.focusState.isFocused
                                && noteTitle.value.text.isBlank()
                    )
                }
            }
            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.update {
                    it.copy(
                        text = event.value
                    )
                }
            }
            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteContent.update {
                    it.copy(
                        isHintVisible = !event.focusState.isFocused
                                && noteContent.value.text.isBlank()
                    )
                }
            }
            is AddEditNoteEvent.ChangeColor -> {
                _noteColor.value = event.color
            }
            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNote(
                            Note(
                                title = noteTitle.value.text,
                                content = noteContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = _noteColor.value,
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(UiEvent.ShowSnackBar(
                            message = e.localizedMessage ?: "Couldn't save note."
                        ))
                    }
                }
            }

        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SaveNote : UiEvent()
    }

}
package com.gabrieltintarescu.noteable.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gabrieltintarescu.noteable.ui.theme.*
import java.lang.Exception

/**
 * @project Noteable
 * @author Gabriel Tintarescu
 * @created 10/26/2022
 */
@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null,
){
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message)

package com.gabrieltintarescu.noteable.feature_note.domain.util

/**
 * @project Noteable
 * @author Gabriel Tintarescu
 * @created 10/26/2022
 */
sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): NoteOrder(orderType)
    class Date(orderType: OrderType): NoteOrder(orderType)
    class Color(orderType: OrderType): NoteOrder(orderType)
}
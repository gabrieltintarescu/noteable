package com.gabrieltintarescu.noteable.feature_note.domain.util

/**
 * @project Noteable
 * @author Gabriel Tintarescu
 * @created 10/26/2022
 */
sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}

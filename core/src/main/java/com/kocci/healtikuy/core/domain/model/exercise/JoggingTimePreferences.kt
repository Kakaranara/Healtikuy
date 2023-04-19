package com.kocci.healtikuy.core.domain.model.exercise

data class JoggingTimePreferences(
    val time: Long?,
    val interval: Int?,
    var isEditing : Boolean
)

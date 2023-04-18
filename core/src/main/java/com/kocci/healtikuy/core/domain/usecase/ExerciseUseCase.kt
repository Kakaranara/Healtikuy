package com.kocci.healtikuy.core.domain.usecase

interface ExerciseUseCase {
}

data class CardioStatus(
    val name: String,
    var isCompleted : Boolean = false
)
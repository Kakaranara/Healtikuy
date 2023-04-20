package com.kocci.healtikuy.core.domain.usecase.exercise

import com.kocci.healtikuy.core.constant.CardioType

interface ExerciseProgressUseCase {
    fun getCardioStatus(): List<CardioType>

}
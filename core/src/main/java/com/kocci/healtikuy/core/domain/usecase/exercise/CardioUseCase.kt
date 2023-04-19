package com.kocci.healtikuy.core.domain.usecase.exercise

import com.kocci.healtikuy.core.constant.CardioType
import kotlinx.coroutines.flow.Flow

sealed class ExerciseTimeIndicator(val data: Long) {
    class Set(time: Long) : ExerciseTimeIndicator(time)
    object NotSet : ExerciseTimeIndicator(0)
}

interface CardioUseCase {
    fun getCardioStatus(): List<CardioType>
    fun getExerciseSchedule(): Flow<ExerciseTimeIndicator>
    fun showFormattedTime(time: Long): String
    suspend fun setExerciseSchedule(timeInString: String, intervalInDays: Int)
}


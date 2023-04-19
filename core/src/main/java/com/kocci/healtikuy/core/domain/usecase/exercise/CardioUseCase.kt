package com.kocci.healtikuy.core.domain.usecase.exercise

import com.kocci.healtikuy.core.constant.CardioType
import kotlinx.coroutines.flow.Flow

sealed class ExerciseTimeIndicator(val data: Long, val interval: Int) {
    class Set(time: Long, interval: Int) : ExerciseTimeIndicator(time, interval)
    object NotSet : ExerciseTimeIndicator(0, 0)
}

interface CardioUseCase {
    fun getCardioStatus(): List<CardioType>
    fun getExerciseSchedule(): Flow<ExerciseTimeIndicator>
    fun showFormattedTime(time: Long): String
    suspend fun setExerciseSchedule(timeInString: String, intervalInDays: Int)
    suspend fun editExerciseSchedule()
}


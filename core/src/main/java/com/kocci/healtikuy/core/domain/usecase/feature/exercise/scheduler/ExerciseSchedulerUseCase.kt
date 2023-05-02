package com.kocci.healtikuy.core.domain.usecase.feature.exercise.scheduler

import kotlinx.coroutines.flow.Flow

interface ExerciseSchedulerUseCase {
    fun getSchedule(): Flow<ExerciseTimeIndicator>
    fun showFormattedTime(time: Long): String
    suspend fun setSchedule(timeInString: String, intervalInDays: Int)
    suspend fun editSchedule()
}
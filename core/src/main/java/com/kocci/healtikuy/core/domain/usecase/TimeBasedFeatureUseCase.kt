package com.kocci.healtikuy.core.domain.usecase

import kotlinx.coroutines.flow.Flow

interface TimeBasedFeatureUseCase<T> {
    suspend fun getAllData(): List<T>
    fun getDataProgress(): Flow<T>
    fun getSchedule(): Flow<TimeIndicator>
    fun isTheTimeWithin1Hours(time: Long): Boolean
    fun showFormattedTime(time: Long): String
    fun setScheduleForNotification(time: Long)

    suspend fun setSchedule(time: Long)
    suspend fun insertNewData(data: T)
    suspend fun completeMission(data: T)
    suspend fun deleteHistory()
}

sealed class TimeIndicator(val time: Long) {
    class Set(data: Long) : TimeIndicator(data)
    object NotSet : TimeIndicator(0)
}


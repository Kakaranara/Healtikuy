package com.kocci.healtikuy.core.domain.usecase.sleep

import com.kocci.healtikuy.core.domain.model.Sleep
import kotlinx.coroutines.flow.Flow


interface SleepUseCase {
    suspend fun getAllData(): List<Sleep>
    fun getSleepProgress(): Flow<Sleep>
    fun getSchedule(): Flow<SleepIndicator>
    fun isTheTimeWithin1Hours(time: Long): Boolean
    fun showFormattedSetTime(time: Long): String
    fun setScheduleForNotification(sleep: Sleep, time: Long)

    suspend fun setSchedule(time: Long)
    suspend fun insertNewData(sleep: Sleep)
    suspend fun completeMission(sleep: Sleep)
}
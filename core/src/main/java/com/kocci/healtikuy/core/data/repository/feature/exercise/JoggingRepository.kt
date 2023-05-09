package com.kocci.healtikuy.core.data.repository.feature.exercise

import com.kocci.healtikuy.core.data.local.LocalDataSource
import com.kocci.healtikuy.core.data.local.entity.exercise.JoggingEntity
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesManager
import com.kocci.healtikuy.core.domain.repository.feature.exercise.ICardioRepository
import com.kocci.healtikuy.core.domain.usecase.feature.exercise.scheduler.ExerciseTimeIndicator
import com.kocci.healtikuy.core.util.helper.PointsManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class JoggingRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val preference: UserPreferencesManager
) : ICardioRepository<JoggingEntity> {
    override suspend fun getAllData(): List<JoggingEntity> {
        return localDataSource.getAllJoggingData()
    }

    override fun getLatestData(): Flow<JoggingEntity?> {
        return localDataSource.getJoggingLastRow()
    }

    override fun getSchedule(): Flow<ExerciseTimeIndicator> {
        return preference.joggingTimePreference.map {
            if (it.time == null || it.interval == null || it.isEditing) {
                ExerciseTimeIndicator.NotSet
            } else {
                val time = it.time
                val int = it.interval
                ExerciseTimeIndicator.Set(time, int)
            }
        }
    }

    override suspend fun updateData(data: JoggingEntity) {
        localDataSource.updateJoggingData(data)
    }

    override suspend fun insertNewData(data: JoggingEntity) {
        localDataSource.insertJoggingData(data)
    }

    override suspend fun setSchedule(time: Long, interval: Int) {
        preference.changeJoggingTime(time, interval, false)
    }

    override suspend fun editSchedule() {
        preference.editJoggingTime(true)
    }

    override suspend fun updatePoints() {
        preference.addPoints(PointsManager.EXERCISE_POINT)
    }

    override suspend fun deleteAllData() {
        localDataSource.clearJogging()
    }
}
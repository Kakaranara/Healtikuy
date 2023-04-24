package com.kocci.healtikuy.core.data.repository.exercise

import com.kocci.healtikuy.core.data.local.LocalDataSource
import com.kocci.healtikuy.core.data.local.entity.exercise.StaticBikeEntity
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesManager
import com.kocci.healtikuy.core.domain.usecase.exercise.scheduler.ExerciseTimeIndicator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StaticBikeRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val preferencesManager: UserPreferencesManager
) : BaseCardioRepository<StaticBikeEntity>() {
    override suspend fun getAllData(): List<StaticBikeEntity> {
        return localDataSource.getAllStaticBikeData()
    }

    override fun getLatestData(): Flow<StaticBikeEntity?> {
        return localDataSource.getStaticBikeLastRow()
    }

    override fun getSchedule(): Flow<ExerciseTimeIndicator> {
        return preferencesManager.staticBikeTimePreference.map {
            if (it.time == null || it.interval == null || it.isEditing) {
                ExerciseTimeIndicator.NotSet
            } else {
                ExerciseTimeIndicator.Set(it.time, it.interval)
            }
        }
    }

    override suspend fun setSchedule(time: Long, interval: Int) {
        return preferencesManager.changeStaticBikeTime(time, interval)
    }

    override suspend fun editSchedule() {
        preferencesManager.editStaticBikeTime(true)
    }

    override suspend fun updatePoints() {
        preferencesManager.addPoints(1000)
    }

    override suspend fun updateData(data: StaticBikeEntity) {
        localDataSource.updateStaticBikeData(data)
    }

    override suspend fun insertNewData(data: StaticBikeEntity) {
        localDataSource.insertStaticBikeData(data)
    }

}
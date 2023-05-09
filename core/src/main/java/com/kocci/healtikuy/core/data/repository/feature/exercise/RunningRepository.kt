package com.kocci.healtikuy.core.data.repository.feature.exercise

import com.kocci.healtikuy.core.data.local.LocalDataSource
import com.kocci.healtikuy.core.data.local.entity.exercise.RunningEntity
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesManager
import com.kocci.healtikuy.core.domain.repository.feature.exercise.IRunningRepository
import com.kocci.healtikuy.core.domain.usecase.feature.exercise.scheduler.ExerciseTimeIndicator
import com.kocci.healtikuy.core.util.helper.PointsManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RunningRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val preferencesManager: UserPreferencesManager
) : IRunningRepository{
    override suspend fun getAllData(): List<RunningEntity> {
        return localDataSource.getAllRunningData()
    }

    override fun getLatestData(): Flow<RunningEntity?> {
        return localDataSource.getRunningLastRow()
    }

    override fun getSchedule(): Flow<ExerciseTimeIndicator> {
        return preferencesManager.runningTimePreference.map {
            if (it.time == null || it.interval == null || it.isEditing) {
                ExerciseTimeIndicator.NotSet
            } else {
                val time = it.time
                val int = it.interval
                ExerciseTimeIndicator.Set(time, int)
            }
        }
    }

    override suspend fun setSchedule(time: Long, interval: Int) {
        preferencesManager.changeRunningTime(time, interval)
    }

    override suspend fun editSchedule() {
        preferencesManager.editRunningtime(true)
    }

    override suspend fun updatePoints() {
        preferencesManager.addPoints(PointsManager.EXERCISE_POINT)
    }

    override suspend fun updateData(data: RunningEntity) {
        localDataSource.updateRunningData(data)
    }

    override suspend fun insertNewData(data: RunningEntity) {
        localDataSource.insertRunningData(data)
    }

    override suspend fun deleteAllData() {
        localDataSource.clearRunning()
    }

    override suspend fun changeLeaderboards(runningType: Int, time: Long) {
        preferencesManager.changeRunningLeaderboards(runningType, time)
    }
}
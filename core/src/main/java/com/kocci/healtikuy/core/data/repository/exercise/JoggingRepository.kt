package com.kocci.healtikuy.core.data.repository.exercise

import com.kocci.healtikuy.core.data.local.LocalDataSource
import com.kocci.healtikuy.core.data.local.entity.exercise.JoggingEntity
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesManager
import com.kocci.healtikuy.core.domain.usecase.exercise.ExerciseTimeIndicator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class JoggingRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val preference: UserPreferencesManager
) : BaseCardioRepository<JoggingEntity>() {
    override fun getAllData(): List<JoggingEntity> {
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

    override suspend fun setSchedule(time: Long, interval: Int) {
        preference.changeJoggingTime(time, interval)
    }

    override suspend fun editJoggingTime() {
        preference.editJoggingTime(true)
    }
}
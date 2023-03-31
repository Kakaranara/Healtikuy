package com.kocci.healtikuy.core.data.repository

import com.kocci.healtikuy.core.data.local.LocalDataSource
import com.kocci.healtikuy.core.data.local.entity.SleepEntity
import com.kocci.healtikuy.core.domain.repository.ISleepRepository
import com.kocci.healtikuy.core.domain.usecase.SleepIndicator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SleepRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val preferenceRepository: UserPreferencesRepository,
) : ISleepRepository {
    override fun getLatestData(): Flow<SleepEntity?> {
        return localDataSource.getSleepLastRow()
    }

    override suspend fun insertData(entity: SleepEntity) {
        return localDataSource.insertSleep(entity)
    }

    override suspend fun updateData(entity: SleepEntity) {
        return localDataSource.updateSleep(entity)
    }

    override suspend fun updateAndAddPoints(entity: SleepEntity) {
        localDataSource.updateSleep(entity)
        preferenceRepository.addPoints(1000)
    }

    override fun getSetTime(): Flow<SleepIndicator> {
        return preferenceRepository.sleepTimePreference.map {
            if (it == null) {
                SleepIndicator.NotSet
            } else {
                SleepIndicator.Set(it)
            }
        }
    }

    override suspend fun changeSetTime(time: Long) {
        return preferenceRepository.changeSleepTime(time)
    }
}
package com.kocci.healtikuy.core.data.repository.feature

import com.kocci.healtikuy.core.data.local.LocalDataSource
import com.kocci.healtikuy.core.data.local.entity.SunExposureEntity
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesManager
import com.kocci.healtikuy.core.domain.repository.feature.timebased.ISunExposureRepository
import com.kocci.healtikuy.core.domain.usecase.TimeIndicator
import com.kocci.healtikuy.core.util.helper.PointsManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SunExposureRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val userPreferencesManager: UserPreferencesManager
) : ISunExposureRepository {
    override suspend fun getAllData(): List<SunExposureEntity> {
        return localDataSource.getAllSunExposureData()
    }

    override fun getLatestData(): Flow<SunExposureEntity?> {
        return localDataSource.getSunExposureLastRow()
    }

    override fun getSchedule(): Flow<TimeIndicator> {
        return userPreferencesManager.sunExposureTimePreference.map {
            if (it == null) {
                TimeIndicator.NotSet
            } else {
                TimeIndicator.Set(it)
            }
        }
    }

    override suspend fun insertData(entity: SunExposureEntity) {
        localDataSource.insertSunExposure(entity)
    }

    override suspend fun updateData(entity: SunExposureEntity) {
        localDataSource.updateSunExposure(entity)
    }

    override suspend fun updateAndAddPoints(entity: SunExposureEntity) {
        updateData(entity)
        userPreferencesManager.addPoints(PointsManager.SUN_EXPOSURE_POINT)
    }

    override suspend fun changeSchedule(time: Long) {
        userPreferencesManager.changeSunExposureTime(time)
    }

}
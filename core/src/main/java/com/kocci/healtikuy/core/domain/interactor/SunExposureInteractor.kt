package com.kocci.healtikuy.core.domain.interactor

import com.kocci.healtikuy.core.data.repository.SunExposureRepository
import com.kocci.healtikuy.core.domain.model.SunExposure
import com.kocci.healtikuy.core.domain.usecase.TimeIndicator
import com.kocci.healtikuy.core.domain.usecase.sunexposure.SunExposureUseCase
import com.kocci.healtikuy.core.service.AlarmService
import com.kocci.healtikuy.core.util.helper.DateHelper
import com.kocci.healtikuy.core.util.mapper.toDomain
import com.kocci.healtikuy.core.util.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SunExposureInteractor @Inject constructor(
    private val repository: SunExposureRepository,
    private val alarmService: AlarmService
) : SunExposureUseCase {
    override suspend fun getAllData(): List<SunExposure> {
        return repository.getAllData().map { it.toDomain() }
    }

    override fun getDataProgress(): Flow<SunExposure> {
        return repository.getLatestData().map {
            if (it == null) {
                SunExposure().also { insertNewData(it) }
            } else if (!DateHelper.isToday(it.timeStamp)) {
                SunExposure().also { insertNewData(it) }
            } else {
                it.toDomain()
            }
        }
    }

    override fun getSchedule(): Flow<TimeIndicator> {
        return repository.getSchedule()
    }

    override fun isTheTimeWithin1Hours(time: Long): Boolean =
        DateHelper.isTimeWithin1Hours(time)


    override fun showFormattedTime(time: Long): String =
        DateHelper.showHoursAndMinutes(time)


    override fun setScheduleForNotification(time: Long) {
        alarmService.setRepeatingScheduleForSunExposure(time)
    }

    override suspend fun setSchedule(time: Long) {
        repository.changeSchedule(time)
        setScheduleForNotification(time)
    }

    override suspend fun insertNewData(data: SunExposure) {
        repository.insertData(data.toEntity())
    }

    override suspend fun completeMission(data: SunExposure) {
        data.isCompleted = true
        data.timeCompleted = System.currentTimeMillis()
        repository.updateAndAddPoints(data.toEntity())
    }
}
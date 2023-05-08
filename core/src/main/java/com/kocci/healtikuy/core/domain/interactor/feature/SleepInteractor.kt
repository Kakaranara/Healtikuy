package com.kocci.healtikuy.core.domain.interactor.feature

import com.kocci.healtikuy.core.data.repository.feature.SleepRepository
import com.kocci.healtikuy.core.domain.model.Sleep
import com.kocci.healtikuy.core.domain.usecase.TimeIndicator
import com.kocci.healtikuy.core.domain.usecase.feature.SleepUseCase
import com.kocci.healtikuy.core.service.AlarmService
import com.kocci.healtikuy.core.util.helper.DateHelper
import com.kocci.healtikuy.core.util.helper.TipList
import com.kocci.healtikuy.core.util.helper.TipsManager
import com.kocci.healtikuy.core.util.mapper.toDomain
import com.kocci.healtikuy.core.util.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SleepInteractor @Inject constructor(
    private val repository: SleepRepository,
    private val sleepAlarmReceiver: AlarmService,
) : SleepUseCase {

    override fun getSleepTips(): TipList = TipsManager.generateSleepTips()

    override suspend fun getAllData(): List<Sleep> {
        return repository.getAllData().map { it.toDomain() }
    }

    override fun getDataProgress(): Flow<Sleep> {
        return repository.getLatestData().map {
            if (it == null) {
                val sleep = Sleep()
                insertNewData(sleep)
                sleep
            } else if (!DateHelper.isToday(it.timeStamp)) {
                val sleep = Sleep()
                insertNewData(sleep)
                sleep
            } else {
                it.toDomain()
            }
        }
    }

    override suspend fun insertNewData(data: Sleep) {
        repository.insertData(data.toEntity())
    }

    override suspend fun completeMission(data: Sleep) {
        data.isCompleted = true
        data.timeCompleted = System.currentTimeMillis()
        repository.updateAndAddPoints(data.toEntity())
    }

    override fun getSchedule(): Flow<TimeIndicator> {
        return repository.getSchedule()
    }

    override fun isTheTimeWithin1Hours(time: Long): Boolean = DateHelper.isTimeWithin1Hours(time)
    override fun showFormattedTime(time: Long): String = DateHelper.showHoursAndMinutes(time)

    override suspend fun setSchedule(time: Long) {
        repository.changeSchedule(time)
        setScheduleForNotification(time)
    }

    override fun setScheduleForNotification(time: Long) {
        sleepAlarmReceiver.setRepeatingScheduleForSleep(time)
    }

    override suspend fun deleteHistory() {
        repository.deleteAllData()
    }
}
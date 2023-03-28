package com.kocci.healtikuy.core.domain.interactor

import android.content.Context
import android.icu.util.Calendar
import com.kocci.healtikuy.core.data.repository.SleepRepository
import com.kocci.healtikuy.core.data.repository.UserPreferencesRepository
import com.kocci.healtikuy.core.domain.model.Sleep
import com.kocci.healtikuy.core.domain.usecase.SleepIndicator
import com.kocci.healtikuy.core.domain.usecase.SleepUseCase
import com.kocci.healtikuy.core.util.helper.DateHelper
import com.kocci.healtikuy.core.util.helper.FormatHelper
import com.kocci.healtikuy.core.util.mapper.toDomain
import com.kocci.healtikuy.core.util.mapper.toEntity
import com.kocci.healtikuy.core.util.service.AlarmService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalTime
import java.util.*
import javax.inject.Inject

class SleepInteractor @Inject constructor(
    private val repository: SleepRepository,
    private val preferencesRepository: UserPreferencesRepository,
    private val alarmService: AlarmService,
) : SleepUseCase {

    override fun getSleepData(): Flow<Sleep> {
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

    override suspend fun insertNewData(sleep: Sleep) {
        repository.insertData(sleep.toEntity())
    }

    override suspend fun updateNewData(sleep: Sleep) {
        sleep.isCompleted = true
        preferencesRepository.addPoints(1000)
        repository.updateData(sleep.toEntity())
    }

    override fun getSetTime(): Flow<SleepIndicator> {
        return preferencesRepository.sleepTimePreference.map {
            if (it == null) {
                SleepIndicator.NotSet
            } else {
                SleepIndicator.Set(it)
            }
        }
    }

    override fun isTheTimeWithin1Hours(time: Long): Boolean {
        val setTime = Calendar.getInstance()
        setTime.time = Date(time)

        val hours = setTime.get(Calendar.HOUR_OF_DAY)
        val minutes = setTime.get(Calendar.MINUTE)
        val hourString = FormatHelper.pad2StartForTime(hours)
        val minuteString = FormatHelper.pad2StartForTime(minutes)

        val timeInString = "$hourString:$minuteString"
        val localTime = LocalTime.parse(timeInString)
        val localTimeNow = LocalTime.now()

        return localTimeNow.plusHours(1).isAfter(localTime) && localTimeNow.minusHours(1)
            .isBefore(localTime)
    }

    override fun showFormattedSetTime(time: Long): String = DateHelper.showHoursAndMinutes(time)

    override suspend fun changeSetTime(time: Long) {
        //! it's hardcoded for now! need to call sleep object in the database
//        setScheduleForNotification(Sleep(), time)
        return preferencesRepository.changeSleepTime(time)
    }

    override fun setScheduleForNotification(context: Context, sleep: Sleep, time: Long) {
        alarmService.setRepeatingSchedule(context, sleep, time)
    }
}
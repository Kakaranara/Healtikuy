package com.kocci.healtikuy.core.domain.interactor.feature.exercise

import com.kocci.healtikuy.core.constant.CardioType
import com.kocci.healtikuy.core.data.repository.feature.exercise.StaticBikeRepository
import com.kocci.healtikuy.core.domain.model.exercise.StaticBike
import com.kocci.healtikuy.core.domain.usecase.feature.exercise.cardio.StaticBikeUseCase
import com.kocci.healtikuy.core.domain.usecase.feature.exercise.scheduler.ExerciseTimeIndicator
import com.kocci.healtikuy.core.service.AlarmService
import com.kocci.healtikuy.core.util.helper.DateHelper
import com.kocci.healtikuy.core.util.mapper.exercise.toDomain
import com.kocci.healtikuy.core.util.mapper.exercise.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class StaticBikeInteractor @Inject constructor(
    private val repository: StaticBikeRepository,
    private val alarmService: AlarmService
) : StaticBikeUseCase {
    override fun getDataProgress(): Flow<StaticBike> {
        return repository.getLatestData().map {
            if (it == null) {
                val data = StaticBike()
                insertNewData(data)
                data
            } else if (!DateHelper.isToday(it.timeStamp)) {
                val data = StaticBike()
                insertNewData(data)
                data
            } else {
                it.toDomain()
            }
        }
    }

    override suspend fun getAllData(): List<StaticBike> {
        return repository.getAllData().map { it.toDomain() }
    }

    override suspend fun insertNewData(data: StaticBike) {
        repository.insertNewData(data.toEntity())
    }

    override suspend fun completeMission(data: StaticBike) {
        data.isCompleted = true
        repository.updateData(data.toEntity())
        repository.updatePoints()
    }

    override fun getSchedule(): Flow<ExerciseTimeIndicator> {
        return repository.getSchedule()
    }

    override fun showFormattedTime(time: Long): String {
        return DateHelper.showHoursAndMinutes(time)
    }

    override suspend fun setSchedule(timeInString: String, intervalInDays: Int) {
        val time = DateHelper.convertTimeStringToTodayDate(timeInString)
        val intervalInMillis = DateHelper.convertDayToMillis(intervalInDays)
        alarmService.setRepeatingScheduleForCardioExercise(
            time,
            intervalInMillis,
            CardioType.STATIC_BIKE
        )
        repository.setSchedule(time, intervalInDays)
    }

    override suspend fun editSchedule() {
        repository.editSchedule()
    }

    override suspend fun clearHistory() {
        repository.deleteAllData()
    }
}
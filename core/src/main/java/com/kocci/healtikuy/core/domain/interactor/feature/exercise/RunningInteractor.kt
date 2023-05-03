package com.kocci.healtikuy.core.domain.interactor.feature.exercise

import com.kocci.healtikuy.core.constant.CardioType
import com.kocci.healtikuy.core.data.repository.feature.exercise.RunningRepository
import com.kocci.healtikuy.core.domain.model.exercise.Running
import com.kocci.healtikuy.core.domain.usecase.feature.exercise.cardio.RunningUseCase
import com.kocci.healtikuy.core.domain.usecase.feature.exercise.scheduler.ExerciseTimeIndicator
import com.kocci.healtikuy.core.service.AlarmService
import com.kocci.healtikuy.core.util.helper.DateHelper
import com.kocci.healtikuy.core.util.mapper.exercise.toDomain
import com.kocci.healtikuy.core.util.mapper.exercise.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RunningInteractor @Inject constructor(
    private val repository: RunningRepository,
    private val alarmService: AlarmService
) : RunningUseCase {
    override fun getDataProgress(): Flow<Running> {
        return repository.getLatestData().map { entity ->
            if (entity == null) {
                val running = Running()
                insertNewData(running)
                running
            } else if (!DateHelper.isToday(entity.timeStamp)) {
                val running = Running()
                insertNewData(running)
                running
            } else {
                entity.toDomain()
            }
        }
    }

    override suspend fun getAllData(): List<Running> {
        return repository.getAllData().map {
            it.toDomain()
        }
    }

    override suspend fun insertNewData(data: Running) {
        repository.insertNewData(data.toEntity())
    }

    override suspend fun completeMission(data: Running) {
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
        repository.setSchedule(time, intervalInDays)
        val intervalInMillis = DateHelper.convertDayToMillis(intervalInDays)
        alarmService.setRepeatingScheduleForCardioExercise(
            time,
            intervalInMillis,
            CardioType.RUNNING
        )
    }

    override suspend fun editSchedule() {
        repository.editSchedule()
    }
}
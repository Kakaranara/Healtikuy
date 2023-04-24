package com.kocci.healtikuy.core.domain.interactor.exercise

import com.kocci.healtikuy.core.constant.CardioType
import com.kocci.healtikuy.core.data.repository.exercise.JoggingRepository
import com.kocci.healtikuy.core.domain.model.exercise.Jogging
import com.kocci.healtikuy.core.domain.usecase.exercise.cardio.JoggingUseCase
import com.kocci.healtikuy.core.domain.usecase.exercise.scheduler.ExerciseTimeIndicator
import com.kocci.healtikuy.core.service.AlarmService
import com.kocci.healtikuy.core.util.helper.DateHelper
import com.kocci.healtikuy.core.util.mapper.exercise.toDomain
import com.kocci.healtikuy.core.util.mapper.exercise.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class JoggingInteractor @Inject constructor(
    private val repository: JoggingRepository,
    private val alarmService: AlarmService
) : JoggingUseCase {
    override fun getDataProgress(): Flow<Jogging> {
        return repository.getLatestData().map { entity ->
            if (entity == null) {
                Jogging().also { insertNewData(it) }
            } else if (!DateHelper.isToday(entity.timeStamp)) {
                Jogging().also { insertNewData(it) }
            } else {
                entity.toDomain()
            }
        }
    }

    override suspend fun getAllData(): List<Jogging> {
        return repository.getAllData().map {
            it.toDomain()
        }
    }

    override suspend fun insertNewData(data: Jogging) {
        repository.insertNewData(data.toEntity())
    }

    override suspend fun updateData(data: Jogging) {
        data.isCompleted = true
        repository.updateData(data.toEntity())
        repository.updatePoints()
    }

    override fun getSchedule(): Flow<ExerciseTimeIndicator> {
        return repository.getSchedule()
    }

    override suspend fun setSchedule(timeInString: String, intervalInDays: Int) {
        val time = DateHelper.convertTimeStringToTodayDate(timeInString)
        repository.setSchedule(time, intervalInDays)
        val intervalInMillis = (DateHelper.dayInMill * intervalInDays).toLong()
        alarmService.setRepeatingScheduleForCardioExercise(
            time,
            intervalInMillis,
            CardioType.JOGGING
        )
    }

    override fun showFormattedTime(time: Long): String = DateHelper.showHoursAndMinutes(time)

    override suspend fun editSchedule() {
        repository.editSchedule()
    }
}
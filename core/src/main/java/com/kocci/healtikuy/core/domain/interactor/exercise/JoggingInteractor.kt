package com.kocci.healtikuy.core.domain.interactor.exercise

import com.kocci.healtikuy.core.constant.CardioType
import com.kocci.healtikuy.core.data.repository.exercise.JoggingRepository
import com.kocci.healtikuy.core.domain.usecase.exercise.ExerciseTimeIndicator
import com.kocci.healtikuy.core.domain.usecase.exercise.cardio.JoggingUseCase
import com.kocci.healtikuy.core.service.AlarmService
import com.kocci.healtikuy.core.util.helper.DateHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JoggingInteractor @Inject constructor(
    private val repository: JoggingRepository,
    private val alarmService: AlarmService
) : JoggingUseCase {
    override fun getCardioStatus(): List<CardioType> {
        TODO("Not yet implemented")
    }

    override fun getExerciseSchedule(): Flow<ExerciseTimeIndicator> {
        return repository.getSchedule()
    }

    override suspend fun setExerciseSchedule(timeInString: String, intervalInDays: Int) {
        val time = DateHelper.convertTimeStringToTodayDate(timeInString)
        repository.setSchedule(time)
        val intervalInMillis = (DateHelper.dayInMill * intervalInDays).toLong()
        alarmService.setRepeatingScheduleForCardioExercise(time, intervalInMillis)
    }

    override fun showFormattedTime(time: Long): String = DateHelper.showHoursAndMinutes(time)

}
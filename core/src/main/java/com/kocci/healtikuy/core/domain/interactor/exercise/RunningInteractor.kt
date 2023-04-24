package com.kocci.healtikuy.core.domain.interactor.exercise

import com.kocci.healtikuy.core.domain.model.exercise.Running
import com.kocci.healtikuy.core.domain.usecase.exercise.cardio.RunningUseCase
import com.kocci.healtikuy.core.domain.usecase.exercise.scheduler.ExerciseTimeIndicator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RunningInteractor @Inject constructor(

) : RunningUseCase {
    override fun getDataProgress(): Flow<Running> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllData(): List<Running> {
        TODO("Not yet implemented")
    }

    override suspend fun insertNewData(data: Running) {
        TODO("Not yet implemented")
    }

    override suspend fun updateData(data: Running) {
        TODO("Not yet implemented")
    }

    override fun getSchedule(): Flow<ExerciseTimeIndicator> {
        TODO("Not yet implemented")
    }

    override fun showFormattedTime(time: Long): String {
        TODO("Not yet implemented")
    }

    override suspend fun setSchedule(timeInString: String, intervalInDays: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun editSchedule() {
        TODO("Not yet implemented")
    }
}
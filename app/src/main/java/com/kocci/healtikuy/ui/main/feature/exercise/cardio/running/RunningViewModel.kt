package com.kocci.healtikuy.ui.main.feature.exercise.cardio.running

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.domain.model.exercise.Running
import com.kocci.healtikuy.core.domain.usecase.feature.exercise.cardio.RunningUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RunningViewModel @Inject constructor(
    private val runningUseCase: RunningUseCase
) : ViewModel() {
    val schedule = runningUseCase.getSchedule().asLiveData()
    val progress = runningUseCase.getDataProgress().asLiveData()
    suspend fun getAllData() = runningUseCase.getAllData()
    fun showFormattedTime(time: Long) = runningUseCase.showFormattedTime(time)
    fun updateData(data: Running) {
        viewModelScope.launch {
            runningUseCase.completeMission(data)
        }
    }

    fun setExerciseSchedule(time: String, interval: Int) {
        viewModelScope.launch {
            runningUseCase.setSchedule(time, interval)
        }
    }

    fun editSchedule() {
        viewModelScope.launch {
            runningUseCase.editSchedule()
        }
    }
}
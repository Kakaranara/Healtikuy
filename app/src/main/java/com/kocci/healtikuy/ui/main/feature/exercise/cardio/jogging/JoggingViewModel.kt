package com.kocci.healtikuy.ui.main.feature.exercise.cardio.jogging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.domain.model.exercise.Jogging
import com.kocci.healtikuy.core.domain.usecase.feature.exercise.cardio.JoggingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoggingViewModel @Inject constructor(
    private val joggingUseCase: JoggingUseCase
) : ViewModel() {
    fun getSchedule() = joggingUseCase.getSchedule().asLiveData()
    fun getProgressData() = joggingUseCase.getDataProgress().asLiveData()
    suspend fun getAllData() = joggingUseCase.getAllData()

    fun updateData(data: Jogging) {
        viewModelScope.launch {
            joggingUseCase.completeMission(data)
        }
    }

    fun setExerciseSchedule(timeInString: String, interval: Int) {
        viewModelScope.launch {
            joggingUseCase.setSchedule(timeInString, interval)
        }
    }

    fun showFormattedTime(time: Long) = joggingUseCase.showFormattedTime(time)

    fun editSchedule() {
        viewModelScope.launch {
            joggingUseCase.editSchedule()
        }
    }
}
package com.kocci.healtikuy.ui.main.feature.exercise.cardio.staticbike

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.domain.model.exercise.StaticBike
import com.kocci.healtikuy.core.domain.usecase.feature.exercise.cardio.StaticBikeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StaticBikeViewModel @Inject constructor(
    private val staticBikeUseCase: StaticBikeUseCase
) : ViewModel() {
    suspend fun getAllData() = staticBikeUseCase.getAllData()
    val progress = staticBikeUseCase.getDataProgress().asLiveData()
    val schedule = staticBikeUseCase.getSchedule().asLiveData()
    fun showFormattedTime(time: Long) = staticBikeUseCase.showFormattedTime(time)

    fun setExerciseSchedule(time: String, interval: Int) {
        viewModelScope.launch {
            staticBikeUseCase.setSchedule(time, interval)
        }
    }

    fun submitData(data: StaticBike) {
        viewModelScope.launch {
            staticBikeUseCase.completeMission(data)
        }
    }

    fun editSchedule() {
        viewModelScope.launch {
            staticBikeUseCase.editSchedule()
        }
    }
}
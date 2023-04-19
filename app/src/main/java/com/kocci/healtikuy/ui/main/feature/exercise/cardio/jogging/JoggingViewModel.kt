package com.kocci.healtikuy.ui.main.feature.exercise.cardio.jogging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.domain.usecase.exercise.cardio.JoggingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoggingViewModel @Inject constructor(
    private val joggingUseCase: JoggingUseCase
) : ViewModel() {
    fun getSchedule() = joggingUseCase.getExerciseSchedule().asLiveData()
    fun setExerciseSchedule(timeInString: String, interval: Int) {
        viewModelScope.launch {
            joggingUseCase.setExerciseSchedule(timeInString, interval)
        }
    }

    fun showFormattedTime(time: Long) = joggingUseCase.showFormattedTime(time)
}
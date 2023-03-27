package com.kocci.healtikuy.ui.main.feature.sleep

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.domain.usecase.SleepUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SleepViewModel @Inject constructor(
    private val useCase: SleepUseCase,
) : ViewModel() {

    fun getDataModel() = useCase.getSleepData().asLiveData()
    val isTimeSet = useCase.getSetTime().asLiveData()

    fun setTime(time: Long) {
        viewModelScope.launch {
            useCase.changeSetTime(time)
        }
    }
}
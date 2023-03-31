package com.kocci.healtikuy.ui.main.feature.sleep

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.domain.model.Sleep
import com.kocci.healtikuy.core.domain.usecase.SleepUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SleepViewModel @Inject constructor(
    private val useCase: SleepUseCase,
) : ViewModel() {

    val getDataModel = useCase.getSleepData().asLiveData()
    val isTimeSet = useCase.getSetTime().asLiveData()
    fun isTheTimeWithin1Hours(time: Long): LiveData<Boolean> {
        val isTimeReady = MutableLiveData<Boolean>(false)
        isTimeReady.value = useCase.isTheTimeWithin1Hours(time)
        return isTimeReady
    }

    fun showFormattedTime(time: Long) = useCase.showFormattedSetTime(time)

    fun setSchedule(context: Context, sleep: Sleep, time: Long) {
        useCase.setScheduleForNotification(context, sleep, time)
    }

    fun setTime(time: Long) {
        viewModelScope.launch {
            useCase.changeSetTime(time)
        }
    }

    fun completeMission(sleep: Sleep) {
        viewModelScope.launch {
            useCase.completeMission(sleep)
        }
    }
}
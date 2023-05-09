package com.kocci.healtikuy.ui.main.feature.sleep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.domain.model.Sleep
import com.kocci.healtikuy.core.domain.usecase.feature.SleepUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SleepViewModel @Inject constructor(
    private val useCase: SleepUseCase,
) : ViewModel() {

    val getDataModel = useCase.getDataProgress().asLiveData()
    val isTimeSet = useCase.getSchedule().asLiveData()
    fun getTips() = useCase.getSleepTips()
    suspend fun getAllData() = useCase.getAllData()
    fun deleteHistory(){
        viewModelScope.launch {
            useCase.deleteHistory()
        }
    }
    fun isTheTimeWithin1Hours(time: Long): LiveData<Boolean> {
        val isTimeReady = MutableLiveData<Boolean>(false)
        isTimeReady.value = useCase.isTheTimeWithin1Hours(time)
        return isTimeReady
    }

    fun showFormattedTime(time: Long) = useCase.showFormattedTime(time)

    fun setSchedule(time: Long) {
        viewModelScope.launch {
            useCase.setSchedule(time)
        }
    }

    fun completeMission(sleep: Sleep) {
        viewModelScope.launch {
            useCase.completeMission(sleep)
        }
    }
}
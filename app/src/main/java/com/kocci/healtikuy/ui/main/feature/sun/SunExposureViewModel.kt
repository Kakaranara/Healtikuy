package com.kocci.healtikuy.ui.main.feature.sun

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.domain.model.SunExposure
import com.kocci.healtikuy.core.domain.usecase.feature.SunExposureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SunExposureViewModel @Inject constructor(
    private val useCase: SunExposureUseCase
) : ViewModel() {


    val getDataModel = useCase.getDataProgress().asLiveData()
    val isTimeSet = useCase.getSchedule().asLiveData()
    suspend fun getAllData() = useCase.getAllData()
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

    fun completeMission(data: SunExposure) {
        viewModelScope.launch {
            useCase.completeMission(data)
        }
    }
}
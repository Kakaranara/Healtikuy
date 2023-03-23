package com.kocci.healtikuy.ui.main.feature.water

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.domain.model.WaterIntake
import com.kocci.healtikuy.core.domain.usecase.WaterIntakeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WaterIntakeViewModel @Inject constructor(
    private val waterIntakeUseCase: WaterIntakeUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
//i            waterIntakeUseCase.insertData(WaterIntake(0, 0, System.currentTimeMillis() - 76000000))
        }
    }

    val listenMe = waterIntakeUseCase.getWaterIntakeData().asLiveData()

    fun updateData(data: WaterIntake) {
        data.quantity += 1
        viewModelScope.launch {
            waterIntakeUseCase.updateData(data)
        }
    }
}
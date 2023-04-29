package com.kocci.healtikuy.ui.main.feature.water

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.domain.model.WaterIntake
import com.kocci.healtikuy.core.domain.usecase.waterintake.WaterIntakeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WaterIntakeViewModel @Inject constructor(
    private val waterIntakeUseCase: WaterIntakeUseCase
) : ViewModel() {

    val waterIntakeLiveData = waterIntakeUseCase.getWaterIntakeData().asLiveData()

    fun drinkOneGlass(data: WaterIntake) {
        viewModelScope.launch {
            waterIntakeUseCase.updateQuantity(data)
        }
    }

    fun updatePointsForGoals(data: WaterIntake) {
        viewModelScope.launch {
            waterIntakeUseCase.completeMission(data)
        }
    }
}
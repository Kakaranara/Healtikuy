package com.kocci.healtikuy.ui.main.feature.nutrition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.domain.model.AvoidFeature
import com.kocci.healtikuy.core.domain.usecase.feature.AvoidFeatureUseCase
import com.kocci.healtikuy.core.domain.usecase.feature.NutritionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NutritionViewModel @Inject constructor(
    private val nutritionUseCase: NutritionUseCase,
    private val avoidUseCase: AvoidFeatureUseCase
) : ViewModel() {

    val isCheckable = avoidUseCase.isCheckable()
    fun getData() = nutritionUseCase.getNutritionFood().asLiveData()
    fun getUserShouldLimitData() = avoidUseCase.getData().asLiveData()

    fun limitSugar(obj: AvoidFeature) {
        viewModelScope.launch {
            avoidUseCase.iLimitMySugar(obj)
        }
    }

    fun limitFat(obj: AvoidFeature) {
        viewModelScope.launch {
            avoidUseCase.iLimitMyOil(obj)
        }
    }

    suspend fun getAllData() = nutritionUseCase.getAllData()
    fun clearHistory() {
        viewModelScope.launch {
            nutritionUseCase.clearHistory()
        }
    }
}
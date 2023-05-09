package com.kocci.healtikuy.ui.main.feature.nutrition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.domain.usecase.feature.NutritionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NutritionViewModel @Inject constructor(
    private val nutritionUseCase: NutritionUseCase
) : ViewModel() {
    fun getData() = nutritionUseCase.getNutritionFood().asLiveData()
    suspend fun getAllData() = nutritionUseCase.getAllData()
    fun clearHistory(){
        viewModelScope.launch {
            nutritionUseCase.clearHistory()
        }
    }
}
package com.kocci.healtikuy.ui.main.feature.nutrition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.domain.model.Nutrition
import com.kocci.healtikuy.core.domain.usecase.feature.NutritionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddDialogViewModel @Inject constructor(
    private val nutritionUseCase: NutritionUseCase
) : ViewModel() {
    fun addFood(food: Nutrition) {
        viewModelScope.launch {
            nutritionUseCase.addFood(food)
        }
    }
}
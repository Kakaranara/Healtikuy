package com.kocci.healtikuy.ui.main.feature.avoid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.domain.model.AvoidFeature
import com.kocci.healtikuy.core.domain.usecase.AvoidFeatureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvoidSmthViewModel @Inject constructor(
    private val useCase: AvoidFeatureUseCase
) : ViewModel() {
    val data = useCase.getData().asLiveData()
    val checkAble = useCase.isCheckable()


    fun checkSmoke(obj: AvoidFeature) {
        viewModelScope.launch {
            useCase.imNotSmoking(obj)
        }
    }

    fun checkAlcohol(obj: AvoidFeature) {
        viewModelScope.launch {
            useCase.imNotDrinkAlcohol(obj)
        }
    }
}
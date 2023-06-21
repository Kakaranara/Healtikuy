package com.kocci.healtikuy.ui.main.feature.avoid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.domain.model.AvoidFeature
import com.kocci.healtikuy.core.domain.usecase.feature.AvoidFeatureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvoidSmthViewModel @Inject constructor(
    private val useCase: AvoidFeatureUseCase
) : ViewModel() {
    val data = useCase.getData().asLiveData()
    val isCheckable = useCase.isCheckable()
    val neverDoneItLiveData = useCase.neverDoneItListener().asLiveData()

    suspend fun getAwaitedData() : AvoidFeature{
        return useCase.getData().first()
    }

    fun checkAll(obj: AvoidFeature){
        viewModelScope.launch {
            //today check
            useCase.checkAllForToday(obj)
            useCase.imNotSmoking(obj)
            useCase.imNotDrinkAlcohol(obj)
        }
    }

    fun changeNeverDoneState(state: Boolean){
        viewModelScope.launch {
            useCase.changeNeverDone(state)
        }
    }

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
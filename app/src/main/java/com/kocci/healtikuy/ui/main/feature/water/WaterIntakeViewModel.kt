package com.kocci.healtikuy.ui.main.feature.water

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.domain.PreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WaterIntakeViewModel @Inject constructor(
    private val userPreferencesRepository: PreferencesUseCase
) : ViewModel() {

    fun addPoints(point: Long) {
        viewModelScope.launch {
            userPreferencesRepository.updatePoints(point)
        }
    }

    fun setLastLogin() {
        viewModelScope.launch {
            userPreferencesRepository.updateLastLogin()
        }
    }
}
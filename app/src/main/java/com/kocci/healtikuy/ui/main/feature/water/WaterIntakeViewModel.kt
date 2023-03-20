package com.kocci.healtikuy.ui.main.feature.water

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WaterIntakeViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    val flow = userPreferencesRepository.userPreferences

    fun addPoints(point: Long) {
        viewModelScope.launch {
            userPreferencesRepository.addPoints(point)
        }
    }

    fun setLastLogin() {
        viewModelScope.launch {
            userPreferencesRepository.setLastLoginToToday()
        }
    }
}
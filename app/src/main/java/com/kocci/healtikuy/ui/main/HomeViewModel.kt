package com.kocci.healtikuy.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.domain.PreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferencesUseCase: PreferencesUseCase
) : ViewModel() {
    private val flow = preferencesUseCase.getUserPreferences()

    fun addPoints(point: Long) {
        viewModelScope.launch {
            preferencesUseCase.updatePoints(point)
        }
    }

}
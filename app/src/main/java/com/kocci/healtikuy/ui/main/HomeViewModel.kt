package com.kocci.healtikuy.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kocci.healtikuy.core.domain.PreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferencesUseCase: PreferencesUseCase
) : ViewModel() {

    val healthyStatus = preferencesUseCase.listenForStatusChanges().asLiveData()

    fun calculateStatusPercentage(points: Long): Int =
        preferencesUseCase.calculateStatusPercentage(points)


}
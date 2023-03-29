package com.kocci.healtikuy.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kocci.healtikuy.core.domain.usecase.AuthUseCase
import com.kocci.healtikuy.core.domain.usecase.PreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferencesUseCase: PreferencesUseCase,
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    val healthyStatus = preferencesUseCase.listenForStatusChanges().asLiveData()

    fun calculateStatusPercentage(points: Long): Int =
        preferencesUseCase.calculateStatusPercentage(points)

    fun isUserLogin(): Boolean {
        return authUseCase.checkIfUserIsLogged()
    }

}
package com.kocci.healtikuy.ui.main

import androidx.lifecycle.ViewModel
import com.kocci.healtikuy.core.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    fun logout() {
        authUseCase.logout()
    }
}
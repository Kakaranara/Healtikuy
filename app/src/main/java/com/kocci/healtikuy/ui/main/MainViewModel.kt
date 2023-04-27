package com.kocci.healtikuy.ui.main

import androidx.lifecycle.ViewModel
import com.kocci.healtikuy.core.domain.usecase.AuthUseCase
import com.kocci.healtikuy.core.domain.usecase.SynchronizationUseCase
import com.kocci.healtikuy.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val synchronizationUseCase: SynchronizationUseCase,
    private val userUseCase: UserUseCase
) : ViewModel() {

    val isUserLogin = userUseCase.isUserLogin()

    suspend fun syncData() {
        synchronizationUseCase.syncDataIfDayChanged()
    }

    fun logout() {
        authUseCase.logout()
    }
}
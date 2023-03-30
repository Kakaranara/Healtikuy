package com.kocci.healtikuy.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kocci.healtikuy.core.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {
    fun registerUserWithEmailAndPassword(email: String, password: String, username: String) =
        authUseCase.registerWithEmailPassword(email, password, username).asLiveData()

}
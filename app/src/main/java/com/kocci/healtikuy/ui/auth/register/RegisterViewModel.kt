package com.kocci.healtikuy.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kocci.healtikuy.core.domain.usecase.AuthUseCase
import com.kocci.healtikuy.core.domain.usecase.RegisterForm
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {
    fun registerUserWithEmailAndPassword(registerForm: RegisterForm) =
        authUseCase.registerWithEmailPassword(registerForm).asLiveData()

}
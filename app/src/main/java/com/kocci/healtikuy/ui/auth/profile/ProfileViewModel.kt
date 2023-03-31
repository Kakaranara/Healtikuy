package com.kocci.healtikuy.ui.auth.profile

import androidx.lifecycle.ViewModel
import com.kocci.healtikuy.core.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {
    val userData = authUseCase.getUserData()
}
package com.kocci.healtikuy.ui.auth.profile.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kocci.healtikuy.core.domain.model.UserPreferences
import com.kocci.healtikuy.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {
    fun updateProfile(userData: UserPreferences) = userUseCase.updateProfile(userData).asLiveData()
}
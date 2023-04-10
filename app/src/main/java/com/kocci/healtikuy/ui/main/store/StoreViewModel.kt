package com.kocci.healtikuy.ui.main.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kocci.healtikuy.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    userUseCase: UserUseCase
) : ViewModel() {
    val userData = userUseCase.getUserData().asLiveData()
}
package com.kocci.healtikuy.ui.auth.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
) : ViewModel() {

    private var _loginStreakLiveData = MutableLiveData<Int>(0)
    val loginStreakLivedata get() = _loginStreakLiveData

    val userData = userUseCase.getUserData().asLiveData()
    init {
        getLoginStreak()
    }

    private fun getLoginStreak() {
        viewModelScope.launch {
            val loginStreak = userUseCase.getUserLoginStreak()
            _loginStreakLiveData.value = loginStreak
        }
    }
}
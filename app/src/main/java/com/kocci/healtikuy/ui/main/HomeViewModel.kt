package com.kocci.healtikuy.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kocci.healtikuy.core.domain.usecase.SynchronizationUseCase
import com.kocci.healtikuy.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val synchronizationUseCase: SynchronizationUseCase
) : ViewModel() {

    val healthyStatus = userUseCase.listenForPointsChanges().asLiveData()
    fun localSync() = synchronizationUseCase.localSync().asLiveData()
    fun cloudSync() = synchronizationUseCase.cloudSync().asLiveData()

    fun calculateStatusPercentage(points: Long): Int =
        userUseCase.calculateStatusPercentage(points)

    fun isUserLogin(): Boolean {
        return userUseCase.isUserLogin()
    }

    fun getUserData() = userUseCase.getUserData().asLiveData()

}
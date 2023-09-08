package com.kocci.healtikuy.ui.temp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kocci.healtikuy.core.data.local.LocalDataSource
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesManager
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.data.repository.AppRepository
import com.kocci.healtikuy.core.util.helper.DateHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DebugViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val preferencesManager: UserPreferencesManager
) : ViewModel() {
    fun regularSync(time: Int) = appRepository.debugRegularSync(time).asLiveData()
    fun addPoints(points: Long) = flow<Async<Long>> {
        emit(Async.Loading)
        try {
            preferencesManager.addPoints(points)
            emit(Async.Success(points))
        } catch (e: Exception) {
            emit(Async.Error(e.message.toString()))
        }
    }.asLiveData()

    fun setLastLoginToYesterday() {
        viewModelScope.launch {
            preferencesManager.setLastLogin(System.currentTimeMillis() - (DateHelper.dayInMill * 3))
        }
    }
}
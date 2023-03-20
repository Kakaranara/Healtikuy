package com.kocci.healtikuy.core.domain

import com.kocci.healtikuy.core.data.local.preferences.UserPreferences
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferencesInteractor @Inject constructor(
    private val repository: UserPreferencesRepository
) : PreferencesUseCase {
    override fun getUserPreferences(): Flow<UserPreferences> {
        return repository.userPreferences
    }

    override suspend fun updatePoints(points: Long) {
        repository.addPoints(points)
    }

    override suspend fun updateLastLogin() {
        repository.setLastLoginToToday()
    }
}
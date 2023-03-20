package com.kocci.healtikuy.core.domain

import com.kocci.healtikuy.core.data.local.preferences.UserPreferences
import kotlinx.coroutines.flow.Flow

interface PreferencesUseCase {
    fun getUserPreferences(): Flow<UserPreferences>
    suspend fun updatePoints(points: Long)
    suspend fun updateLastLogin()
}
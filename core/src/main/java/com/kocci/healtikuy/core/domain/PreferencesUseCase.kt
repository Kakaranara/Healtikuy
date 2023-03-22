package com.kocci.healtikuy.core.domain

import com.kocci.healtikuy.core.data.local.preferences.UserPreferences
import kotlinx.coroutines.flow.Flow

sealed class HealthyStatusIndicator(val point: Long) {
    object ZERO : HealthyStatusIndicator(0)
    class NEARLY_COMPLETE(points: Long) : HealthyStatusIndicator(points)
    class COMPLETED(points: Long) : HealthyStatusIndicator(points)
}

interface PreferencesUseCase {
    fun getUserPreferences(): Flow<UserPreferences>
    suspend fun updatePoints(points: Long)
    suspend fun updateLastLogin()
    fun listenForStatusChanges(): Flow<HealthyStatusIndicator>

    fun calculateStatusPercentage(points : Long) : Int
}
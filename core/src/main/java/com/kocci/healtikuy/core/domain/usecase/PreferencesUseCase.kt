package com.kocci.healtikuy.core.domain.usecase

import com.kocci.healtikuy.core.data.local.preferences.UserPreferences
import kotlinx.coroutines.flow.Flow

sealed class HealthyStatusIndicator(val point: Long) {
    class Low(points: Long) : HealthyStatusIndicator(points)

    class MidComplete(points: Long) : HealthyStatusIndicator(points)
    class NearlyComplete(points: Long) : HealthyStatusIndicator(points)
    class Completed(points: Long) : HealthyStatusIndicator(points)
}

interface PreferencesUseCase {
    fun getUserPreferences(): Flow<UserPreferences>
    fun listenForStatusChanges(): Flow<HealthyStatusIndicator>
    fun calculateStatusPercentage(points: Long): Int
    suspend fun updatePoints(points: Long)
    suspend fun updateLastLogin()
}
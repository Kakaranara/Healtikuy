package com.kocci.healtikuy.core.domain.usecase

import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow

sealed class HealthyStatusIndicator(val point: Long) {
    class Low(points: Long) : HealthyStatusIndicator(points)
    class MidComplete(points: Long) : HealthyStatusIndicator(points)
    class NearlyComplete(points: Long) : HealthyStatusIndicator(points)
    class Completed(points: Long) : HealthyStatusIndicator(points)
}

interface UserUseCase {
    fun getUserData(): Flow<UserPreferences>
    suspend fun getUserLoginStreak() : Int
    fun isUserLogin(): Boolean
    fun listenForPointsChanges(): Flow<HealthyStatusIndicator>
    fun calculateStatusPercentage(points: Long): Int
    fun updateProfile(userData: UserPreferences): Flow<Async<Unit>>
}
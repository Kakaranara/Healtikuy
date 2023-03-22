package com.kocci.healtikuy.core.domain

import com.kocci.healtikuy.core.constant.GameRules
import com.kocci.healtikuy.core.data.local.preferences.UserPreferences
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesRepository
import kotlinx.coroutines.flow.*
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

    override fun listenForStatusChanges(): Flow<HealthyStatusIndicator> {
        return repository.userPreferences.map {
            if (it.points >= GameRules.MAX_STATUS_POINTS) {
                return@map HealthyStatusIndicator.COMPLETED(it.points)
            } else if (it.points > 0) {
                return@map HealthyStatusIndicator.NEARLY_COMPLETE(it.points)
            } else {
                return@map HealthyStatusIndicator.ZERO
            }
        }
    }

    override fun calculateStatusPercentage(points: Long): Int {
        return if (points > GameRules.MAX_STATUS_POINTS) {
            GameRules.STATUS_COMPLETE_PERCENTAGE
        } else {
            val percentage = (points.toDouble() / GameRules.MAX_STATUS_POINTS.toDouble()) * 100
            return percentage.toInt()
        }
    }


}
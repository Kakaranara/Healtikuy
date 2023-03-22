package com.kocci.healtikuy.core.domain.interactor

import com.kocci.healtikuy.core.constant.GameRules
import com.kocci.healtikuy.core.data.local.preferences.UserPreferences
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesRepository
import com.kocci.healtikuy.core.domain.usecase.HealthyStatusIndicator
import com.kocci.healtikuy.core.domain.usecase.PreferencesUseCase
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
            val points = it.points
            val progress = calculateStatusPercentage(points)
            if (progress == GameRules.STATUS_COMPLETE_PERCENTAGE) {
                HealthyStatusIndicator.Completed(points)
            } else if (progress >= 75) {
                HealthyStatusIndicator.NearlyComplete(points)
            } else if (progress >= 35) {
                HealthyStatusIndicator.MidComplete(points)
            } else {
                HealthyStatusIndicator.Low(points)
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
package com.kocci.healtikuy.core.domain.interactor

import com.kocci.healtikuy.core.constant.GameRules
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.data.repository.UserRepository
import com.kocci.healtikuy.core.domain.model.UserPreferences
import com.kocci.healtikuy.core.domain.usecase.HealthyStatusIndicator
import com.kocci.healtikuy.core.domain.usecase.UserUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val repository: UserRepository,
) : UserUseCase {
    override fun getUserData(): Flow<UserPreferences> {
        return repository.getUserPreferences()
    }

    override fun listenForStatusChanges(): Flow<HealthyStatusIndicator> {
        return repository.getUserPreferences().map {
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

    override fun isUserLogin(): Boolean {
        return repository.isUserLogin()
    }

    override fun updateProfile(userData: UserPreferences): Flow<Async<Unit>> {
        return repository.updateUserProfile(userData)
    }
}
package com.kocci.healtikuy.core.domain.repository

import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun getRawUserPreference(): Flow<UserPreferences>
    fun getUserDataAndUpdatePreference(): Flow<UserPreferences>
    fun updateUserProfile(userData: UserPreferences): Flow<Async<Unit>>
    fun isUserLogin(): Boolean
}
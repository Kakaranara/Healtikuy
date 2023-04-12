package com.kocci.healtikuy.core.domain.repository

import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.UserPreferences
import com.kocci.healtikuy.core.util.store.CharacterInStore
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun getUserPreferences(): Flow<UserPreferences>
    fun updateUserProfile(userData: UserPreferences): Flow<Async<Unit>>
    fun isUserLogin(): Boolean
    fun buyAvatar(characterInStore: CharacterInStore): Flow<Async<Unit>>
}
package com.kocci.healtikuy.core.data.repository

import androidx.core.net.toUri
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesManager
import com.kocci.healtikuy.core.data.remote.RemoteDataSource
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.UserPreferences
import com.kocci.healtikuy.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userPreferenceManager: UserPreferencesManager,
    private val remoteDataSource: RemoteDataSource
) : IUserRepository {
    override fun getRawUserPreference(): Flow<UserPreferences> {
        return userPreferenceManager.userPreferences
    }

    override fun getUserDataAndUpdatePreference(): Flow<UserPreferences> {
        return userPreferenceManager.userPreferences
    }

    override fun isUserLogin(): Boolean {
        return remoteDataSource.getFirebaseUser() != null
    }

    override fun updateUserProfile(userData: UserPreferences): Flow<Async<Unit>> = flow {
        emit(Async.Loading)
        val firebaseUser = remoteDataSource.getFirebaseUser()
        try {
            val updateProfileRequest = userProfileChangeRequest {
                displayName = userData.username
                photoUri = userData.photoUrl.toUri()
            }
            firebaseUser?.updateProfile(updateProfileRequest)?.await()
            userPreferenceManager.updateUserProfile(
                userData.username,
                userData.photoUrl,
                userData.email
            )
            emit(Async.Success(Unit))
        } catch (e: Exception) {
            emit(Async.Error(e.message.toString()))
        }

    }
}
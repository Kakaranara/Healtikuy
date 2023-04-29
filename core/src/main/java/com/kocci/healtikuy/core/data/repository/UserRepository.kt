package com.kocci.healtikuy.core.data.repository

import androidx.core.net.toUri
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesManager
import com.kocci.healtikuy.core.data.remote.RemoteDataSource
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.UserPreferences
import com.kocci.healtikuy.core.domain.repository.IUserRepository
import com.kocci.healtikuy.core.util.store.CharacterInStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userPreferenceManager: UserPreferencesManager,
    private val remoteDataSource: RemoteDataSource
) : IUserRepository {
    override fun getUserPreferences(): Flow<UserPreferences> {
        return userPreferenceManager.userPreferences
    }

    override suspend fun getRawUserPreferences(): UserPreferences {
        return getUserPreferences().first()
    }

    override fun isUserLogin(): Boolean {
        return remoteDataSource.getFirebaseUser() != null
    }

    override fun updateUserProfile(userData: UserPreferences): Flow<Async<Unit>> = flow {
        emit(Async.Loading)
        val firebaseUser = remoteDataSource.getFirebaseUser() as FirebaseUser
        try {
            val updateProfileRequest = userProfileChangeRequest {
                displayName = userData.username
                photoUri = userData.avatar.toUri()
            }
            firebaseUser.updateProfile(updateProfileRequest).await()
            userPreferenceManager.updateUserProfile(
                userData.username,
                userData.avatar,
            )
            remoteDataSource.updateAvatar(firebaseUser.uid, userData.avatar)
            emit(Async.Success(Unit))
        } catch (e: Exception) {
            emit(Async.Error(e.message.toString()))
        }

    }

    override fun buyAvatar(characterInStore: CharacterInStore): Flow<Async<Unit>> = flow {
        emit(Async.Loading)
        try {
            val preference = userPreferenceManager.userPreferences.first()
            val newInventory = preference.inventory.toMutableSet().apply {
                add(characterInStore.name)
            }.toSet()
            val coinNow = preference.coin - characterInStore.price
            userPreferenceManager.changeCoin(coinNow)
            userPreferenceManager.changeInventory(newInventory)

            val fbUser = remoteDataSource.getFirebaseUser() as FirebaseUser
            remoteDataSource.updateAfterBuyAvatar(fbUser.uid, coinNow, newInventory)
            emit(Async.Success(Unit))
        } catch (e: Exception) {
            emit(Async.Error(e.message.toString()))
        }
    }
}
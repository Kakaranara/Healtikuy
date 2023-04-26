package com.kocci.healtikuy.core.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesManager
import com.kocci.healtikuy.core.data.remote.RemoteDataSource
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val preferencesManager: UserPreferencesManager
) {
    fun getRemoteDataThenSyncToLocal(): Flow<Async<Unit>> = flow {
        emit(Async.Loading)
        val fbUser = remoteDataSource.getFirebaseUser() as FirebaseUser
        try {
            val remoteSource = remoteDataSource.getUserData(fbUser.uid).data
            remoteSource?.let { map ->
                val newUsers = UserPreferences(
                    lastLogin = System.currentTimeMillis(),
                    points = map["points"] as Long,
                    coin = (map["coin"] as Long).toInt(),
                    username = fbUser.displayName.toString(),
                    email = fbUser.email.toString(),
                    avatar = map["avatar"] as String,
                    inventory = (map["inventory"] as ArrayList<String>).toSet(),
                    map["running_100"] as Long,
                    map["running_200"] as Long,
                    map["running_400"] as Long
                )
                preferencesManager.loginSync(newUsers)
                emit(Async.Success(Unit))
            } ?: Log.e("AppRepo", "remote data is NULL")
        } catch (e: Exception) {
            emit(Async.Error(e.message.toString()))
            Log.e("APP REPO", "getRemoteDataThenSyncToLocal: SYNC FAILED")
        }
    }

    fun getLocalDataThenSyncToCloud(): Flow<Async<Unit>> = flow {
        emit(Async.Loading)
        val fbUser = remoteDataSource.getFirebaseUser() as FirebaseUser
        try {
            val localData = preferencesManager.userPreferences.first()
            val firestoreDocument = hashMapOf<String, Any>(
                "avatar" to localData.avatar,
                "inventory" to (localData.inventory.toMutableList() as ArrayList<String>), //? firestore just accept a few datatypes
                "coin" to localData.coin,
                "points" to localData.points,
                "running_100" to localData.running100MPoint,
                "running_200" to localData.running200MPoint,
                "running_400" to localData.running400MPoint,
                "last_login" to localData.lastLogin
            )
            remoteDataSource.updateUserData(fbUser.uid, firestoreDocument)
            emit(Async.Success(Unit))
        } catch (e: Exception) {
            Log.e("APP REPO", "${e.message}")
            emit(Async.Error(e.message.toString()))
        }
    }

    suspend fun syncDataIfDayChanged() {
        val pref = preferencesManager.userPreferences.first()

    }

}
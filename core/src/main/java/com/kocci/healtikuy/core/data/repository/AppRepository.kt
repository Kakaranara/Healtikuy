package com.kocci.healtikuy.core.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesManager
import com.kocci.healtikuy.core.data.remote.RemoteDataSource
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.UserPreferences
import com.kocci.healtikuy.core.util.helper.DateHelper
import com.kocci.healtikuy.core.util.helper.PointsManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * This repository handles synchronization data.
 * You might be interested looking at regularSync().
 */
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
                    username = map["username"] as String,
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
                "username" to localData.username,
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

    suspend fun regularSync() {
        val localData = preferencesManager.userPreferences.first()
        val lastLogin = localData.lastLogin
        if (!DateHelper.isToday(lastLogin)) {
            Log.e("SYNC", "regularSync: SYNC TRIGGERED")
            preferencesManager.addLoginStreak()
            reducePointIfAbsent(lastLogin)
            addCoinPerLoginStreak()
            val fbUser = remoteDataSource.getFirebaseUser() as FirebaseUser
            try {
                val document = hashMapOf<String, Any>(
                    "avatar" to localData.avatar,
                    "inventory" to (localData.inventory.toMutableList() as ArrayList<String>), //? firestore just accept a few datatypes
                    "coin" to localData.coin,
                    "points" to localData.points,
                    "username" to localData.username,
                    "running_100" to localData.running100MPoint,
                    "running_200" to localData.running200MPoint,
                    "running_400" to localData.running400MPoint,
                    "last_login" to localData.lastLogin
                )
                remoteDataSource.updateUserData(fbUser.uid, document)
                preferencesManager.setLastLoginToToday()
                Log.e("APP REPO SYNC", "regular sync success")
            } catch (e: Exception) {
                Log.e("APP REPO SYNC", "FAILED with msg : ${e.message}")
            }
        }
    }

    private suspend fun reducePointIfAbsent(lastLogin: Long) {
        val missingDays =
            DateHelper.calculateDayDiff(lastLogin, currentTime = System.currentTimeMillis())
        //the day after tomorrow
        //because if it only tomorrow then it was a regular sync.
        Log.e("APP Repository", "reducePointIfAbsent: POINT REDUCED")
        if (missingDays > 1) {
            val pointReduced = missingDays * PointsManager.MISSING_POINT
            preferencesManager.reducePoint(pointReduced)
            preferencesManager.resetLoginStreak()
        }
    }

    private suspend fun addCoinPerLoginStreak() {
        val loginStreak = preferencesManager.loginStreak.first()
        if (loginStreak != 0) {
            val bonus = (loginStreak / 3) + 1
            val coinReward = PointsManager.DAILY_LOGIN_COIN * bonus
            preferencesManager.addCoin(coinReward)
        }
    }

    //!Debug function : put in top of regular sync
    private suspend fun forceLastLoginYesterday() {
        /**
         * You might determine the time to other time than yesterday.
         * Determine the time : https://www.unixtimestamp.com/
         * After convert, multiply it by 1000. because converted unix not in millis.
         */
        //set to yesterday
        preferencesManager.setLastLogin(System.currentTimeMillis() - 86500000)
//        preferencesManager.setLastLogin(1684191668000L)
    }

}
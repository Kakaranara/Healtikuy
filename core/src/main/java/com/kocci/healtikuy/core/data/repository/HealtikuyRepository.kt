package com.kocci.healtikuy.core.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.kocci.healtikuy.core.data.local.LocalDataSource
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesManager
import com.kocci.healtikuy.core.data.remote.RemoteDataSource
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.Challenge
import com.kocci.healtikuy.core.domain.repository.IHealtikuyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HealtikuyRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val preferences: UserPreferencesManager,
    private val remoteDataSource: RemoteDataSource
) : IHealtikuyRepository {
    override fun getChallenges(): Flow<Async<List<Challenge>>> = flow {
        emit(Async.Loading)
        try {
            val fbUser = remoteDataSource.getFirebaseUser() as FirebaseUser
            var challengeList = remoteDataSource.getUserChallenges(fbUser.uid).documents
            if (challengeList.isEmpty()) {
                remoteDataSource.createUserChallenges(fbUser.uid)
                challengeList = remoteDataSource.getUserChallenges(fbUser.uid).documents
            }
            val data = challengeList.map {
                val challengeId = it.id
                val data = it.data!!
                val challengeDesc = remoteDataSource.getChallengesData(challengeId).data!!
                Challenge(
                    challengeId = challengeId,
                    name = challengeDesc["name"] as String,
                    coinRewards = (challengeDesc["coin_reward"] as Long).toInt(),
                    isCompleted = data["is_completed"] as Boolean,
                    category = challengeDesc["category"] as String,
                    pointRequired = (challengeDesc["point_required"] as Long).toInt(),
                    loginRequired = (challengeDesc["login_required"] as Long).toInt()
                )
            }
            Log.e(TAG, "getChallenges: data is :$data")
            emit(Async.Success(data))
        } catch (e: Exception) {
            Log.e(TAG, "getChallenges error: ${e.message} ")
            emit(Async.Error(e.message.toString()))
        }
    }

    override fun completeChallenges(cId: String): Flow<Async<Unit>> = flow {
        emit(Async.Loading)
        try {
            val fbUser = remoteDataSource.getFirebaseUser() as FirebaseUser
            remoteDataSource.updateChallengeData(fbUser.uid, cId, true)
            emit(Async.Success(Unit))
        } catch (e: Exception) {
            Log.e(TAG, "completeChallenges: ERROR MESSGE: ${e.message}")
            emit(Async.Error("error. ${e.message}"))
        }
    }

    override suspend fun addCoins(coin: Int) {
        preferences.addCoin(coin)
    }

    companion object {
        private const val TAG = "HealtikuyRepository"
    }
}
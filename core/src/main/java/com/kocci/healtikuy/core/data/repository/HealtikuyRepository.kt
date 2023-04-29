package com.kocci.healtikuy.core.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.kocci.healtikuy.core.data.local.LocalDataSource
import com.kocci.healtikuy.core.data.remote.RemoteDataSource
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.Challenge
import com.kocci.healtikuy.core.domain.repository.IHealtikuyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class HealtikuyRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
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

    override suspend fun updateChallenges(entity: Challenge) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TAG = "HealtikuyRepository"
    }
}
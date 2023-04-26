package com.kocci.healtikuy.core.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.kocci.healtikuy.core.data.local.LocalDataSource
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesManager
import com.kocci.healtikuy.core.data.remote.RemoteDataSource
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.UserPreferences
import com.kocci.healtikuy.core.domain.repository.IAuthRepository
import com.kocci.healtikuy.core.domain.usecase.LoginForm
import com.kocci.healtikuy.core.domain.usecase.RegisterForm
import com.kocci.healtikuy.core.service.FirstTimeService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val preferencesManager: UserPreferencesManager,
    private val localDataSource: LocalDataSource
) : IAuthRepository {

    companion object {
        private const val TAG = "AuthRepository"
    }

    override fun registerUserWithEmailPassword(form: RegisterForm): Flow<Async<Unit>> = flow {
        emit(Async.Loading)
        val firebaseAuth = remoteDataSource.getFirebaseAuth()
        try {
            firebaseAuth.createUserWithEmailAndPassword(
                form.email, form.password
            ).await()
            val fbUser = firebaseAuth.currentUser as FirebaseUser
            fbUser.updateProfile(userProfileChangeRequest {
                displayName = form.username
            }).await()
            preferencesManager.loginSync(
                FirstTimeService.getFirstTimeSync(
                    fbUser.displayName.toString(),
                    fbUser.email.toString()
                )
            )
            remoteDataSource.createUserDataFirstTime(fbUser.uid, fbUser.displayName.toString())
            emit(Async.Success(Unit))
        } catch (e: Exception) {
            Log.e(TAG, "registerUserWithEmailPassword: ${e.message.toString()}")
            emit(Async.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override fun loginUserWithEmailPassword(form: LoginForm): Flow<Async<Unit>> = flow {
        emit(Async.Loading)
        val firebaseAuth = remoteDataSource.getFirebaseAuth()
        try {
            firebaseAuth.signInWithEmailAndPassword(form.email, form.password).await()
            val fbUser = firebaseAuth.currentUser as FirebaseUser
            preferencesManager.updateUserProfile(
                username = fbUser.displayName.toString(),
                avatar = fbUser.photoUrl.toString(),
            )
            val snapshot = remoteDataSource.getUserData(fbUser.uid)
            val remoteData = snapshot.data
            remoteData?.let { map ->
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
            } ?: Log.e(TAG, "REMOTE DATA IS NULL")
            emit(Async.Success(Unit))
        } catch (e: Exception) {
            emit(Async.Error(e.message.toString()))
        }
    }

    override fun logout() {
        CoroutineScope(Dispatchers.IO + Job()).launch {
            remoteDataSource.getFirebaseAuth().signOut()
            localDataSource.clearDatabase()
            preferencesManager.clearAllData()
        }
    }


}
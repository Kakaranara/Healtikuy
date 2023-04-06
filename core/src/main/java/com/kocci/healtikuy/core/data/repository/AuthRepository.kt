package com.kocci.healtikuy.core.data.repository

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.kocci.healtikuy.core.data.local.LocalDataSource
import com.kocci.healtikuy.core.data.local.preferences.UserPreferencesManager
import com.kocci.healtikuy.core.data.remote.RemoteDataSource
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.repository.IAuthRepository
import com.kocci.healtikuy.core.domain.usecase.LoginForm
import com.kocci.healtikuy.core.domain.usecase.RegisterForm
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
    override fun registerUserWithEmailPassword(form: RegisterForm): Flow<Async<Unit>> =
        flow {
            emit(Async.Loading)
            val firebaseAuth = remoteDataSource.getFirebaseAuth()
            try {
                firebaseAuth.createUserWithEmailAndPassword(
                    form.email,
                    form.password
                ).await()
                val profileUpdate = userProfileChangeRequest {
                    displayName = form.username
                }
                firebaseAuth.currentUser?.updateProfile(profileUpdate)?.await()
                val fbUser = firebaseAuth.currentUser as FirebaseUser
                preferencesManager.updateUserProfile(
                    username = fbUser.displayName.toString(),
                    photoUrl = fbUser.photoUrl.toString(),
                    email = fbUser.email.toString()
                )
                emit(Async.Success(Unit))
            } catch (e: Exception) {
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
                photoUrl = fbUser.photoUrl.toString(),
                email = fbUser.email.toString()
            )
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
package com.kocci.healtikuy.core.data.repository

import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.kocci.healtikuy.core.data.remote.RemoteDataSource
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.repository.IAuthRepository
import com.kocci.healtikuy.core.domain.usecase.LoginForm
import com.kocci.healtikuy.core.domain.usecase.RegisterForm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
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
            emit(Async.Success(Unit))
        } catch (e: Exception) {
            emit(Async.Error(e.message.toString()))
        }
    }

}
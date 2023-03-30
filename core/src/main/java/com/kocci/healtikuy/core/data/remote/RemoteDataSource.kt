package com.kocci.healtikuy.core.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.usecase.LoginForm
import com.kocci.healtikuy.core.domain.usecase.RegisterForm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val rootRef: FirebaseFirestore,
) {

    fun isUserLogin(): Boolean {
        return firebaseAuth.currentUser != null
    }

    fun registerUserWithEmailPassword(registerForm: RegisterForm): Flow<Async<Unit>> = flow {
        emit(Async.Loading)
        try {
            firebaseAuth.createUserWithEmailAndPassword(
                registerForm.email,
                registerForm.password
            ).await()
            val profileUpdate = userProfileChangeRequest {
                displayName = registerForm.username
            }
            firebaseAuth.currentUser?.updateProfile(profileUpdate)?.await()
            emit(Async.Success(Unit))
        } catch (e: Exception) {
            emit(Async.Error(e.message.toString()))
        }

    }.flowOn(Dispatchers.IO)


    fun loginUserWithEmailPassword(loginForm: LoginForm): Flow<Async<Unit>> = flow {
        emit(Async.Loading)
        try {
            firebaseAuth.signInWithEmailAndPassword(loginForm.email, loginForm.password).await()
            emit(Async.Success(Unit))
        } catch (e: Exception) {
            emit(Async.Error(e.message.toString()))
        }
    }

}
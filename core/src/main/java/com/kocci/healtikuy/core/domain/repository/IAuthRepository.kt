package com.kocci.healtikuy.core.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.usecase.LoginForm
import com.kocci.healtikuy.core.domain.usecase.RegisterForm
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    fun getUserInstance() : FirebaseUser?
    fun registerUserWithEmailPassword(form: RegisterForm): Flow<Async<Unit>>

    fun loginUserWithEmailPassword(form: LoginForm): Flow<Async<Unit>>
}
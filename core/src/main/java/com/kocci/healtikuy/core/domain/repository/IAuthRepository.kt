package com.kocci.healtikuy.core.domain.repository

import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.usecase.RegisterForm
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    fun isUserLogin(): Boolean
    fun registerUserWithEmailPassword(form: RegisterForm): Flow<Async<Unit>>

    fun loginUserWithEmailPassword(email: String, password: String): Flow<Async<Unit>>
}
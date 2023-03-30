package com.kocci.healtikuy.core.domain.repository

import com.kocci.healtikuy.core.data.remote.model.Async
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    fun isUserLogin(): Boolean
    fun registerUserWithEmailPassword(
        email: String,
        password: String,
        username: String,
    ): Flow<Async<Unit>>

    fun loginUserWithEmailPassword(email: String, password: String): Flow<Async<Unit>>
}
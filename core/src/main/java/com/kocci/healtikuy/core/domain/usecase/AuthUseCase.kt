package com.kocci.healtikuy.core.domain.usecase

import com.kocci.healtikuy.core.data.remote.model.Async
import kotlinx.coroutines.flow.Flow

data class RegisterForm(
    val email: String,
    val password: String,
    val username: String,
)

data class LoginForm(
    val email: String,
    val password: String,
)

interface AuthUseCase {
    fun checkIfUserIsLogged(): Boolean

    fun registerWithEmailPassword(
        email: String,
        password: String,
        username: String,
    ): Flow<Async<Unit>>

    fun loginWithEmailPassword(email: String, password: String): Flow<Async<Unit>>
}
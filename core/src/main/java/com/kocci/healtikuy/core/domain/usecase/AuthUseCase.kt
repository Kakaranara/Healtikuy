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
    fun registerWithEmailPassword(form: RegisterForm): Flow<Async<Unit>>
    fun loginWithEmailPassword(form: LoginForm): Flow<Async<Unit>>
    fun logout()
}
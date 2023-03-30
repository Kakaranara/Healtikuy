package com.kocci.healtikuy.core.domain.interactor

import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.data.repository.AuthRepository
import com.kocci.healtikuy.core.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthInteractor @Inject constructor(private val authRepository: AuthRepository) : AuthUseCase {
    override fun checkIfUserIsLogged(): Boolean {
        return authRepository.isUserLogin()
    }

    override fun registerWithEmailPassword(
        email: String,
        password: String,
        username: String
    ): Flow<Async<Unit>> {
        return authRepository.registerUserWithEmailPassword(email, password, username)
    }

    override fun loginWithEmailPassword(email: String, password: String): Flow<Async<Unit>> {
        TODO("Not yet implemented")
    }
}
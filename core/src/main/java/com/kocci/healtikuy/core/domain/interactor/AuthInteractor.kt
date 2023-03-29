package com.kocci.healtikuy.core.domain.interactor

import com.kocci.healtikuy.core.data.repository.AuthRepository
import com.kocci.healtikuy.core.domain.usecase.AuthUseCase
import javax.inject.Inject

class AuthInteractor @Inject constructor(private val authRepository: AuthRepository) : AuthUseCase {
    override fun checkIfUserIsLogged(): Boolean {
        return authRepository.isUserLogin()
    }
}
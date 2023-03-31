package com.kocci.healtikuy.core.domain.interactor

import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.data.repository.AuthRepository
import com.kocci.healtikuy.core.domain.model.User
import com.kocci.healtikuy.core.domain.usecase.AuthUseCase
import com.kocci.healtikuy.core.domain.usecase.LoginForm
import com.kocci.healtikuy.core.domain.usecase.RegisterForm
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthInteractor @Inject constructor(private val authRepository: AuthRepository) : AuthUseCase {
    override fun checkIfUserIsLogged(): Boolean {
        return authRepository.getUserInstance() != null
    }

    override fun getUserData(): User {
        val firebaseUser = authRepository.getUserInstance()
        return if (firebaseUser != null) {
            User(
                username = firebaseUser.displayName ?: "Username Not Set Yet",
                email = firebaseUser.email ?: "Email Not Set"
            )
        } else {
            User("null", "null")
        }
    }


    override fun registerWithEmailPassword(form: RegisterForm): Flow<Async<Unit>> {
        return authRepository.registerUserWithEmailPassword(form)
    }

    override fun loginWithEmailPassword(form: LoginForm): Flow<Async<Unit>> {
        return authRepository.loginUserWithEmailPassword(form)
    }
}
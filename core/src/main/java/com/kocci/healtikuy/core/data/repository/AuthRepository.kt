package com.kocci.healtikuy.core.data.repository

import com.kocci.healtikuy.core.data.remote.RemoteDataSource
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.repository.IAuthRepository
import com.kocci.healtikuy.core.domain.usecase.LoginForm
import com.kocci.healtikuy.core.domain.usecase.RegisterForm
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : IAuthRepository {
    override fun isUserLogin(): Boolean {
        return remoteDataSource.isUserLogin()
    }

    override fun registerUserWithEmailPassword(form: RegisterForm): Flow<Async<Unit>> {
        return remoteDataSource.registerUserWithEmailPassword(form)
    }

    override fun loginUserWithEmailPassword(form: LoginForm): Flow<Async<Unit>> {
        return remoteDataSource.loginUserWithEmailPassword(form)
    }

}
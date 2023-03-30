package com.kocci.healtikuy.core.data.repository

import com.kocci.healtikuy.core.data.remote.RemoteDataSource
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : IAuthRepository {
    override fun isUserLogin(): Boolean {
        return remoteDataSource.isUserLogin()
    }

    override fun registerUserWithEmailPassword(
        email: String,
        password: String,
        username: String,
    ): Flow<Async<Unit>> {
        return remoteDataSource.registerUserWithEmailPassword(email, password, username)
    }

    override fun loginUserWithEmailPassword(email: String, password: String): Flow<Async<Unit>> {
        TODO("Not yet implemented")
    }

}
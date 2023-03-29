package com.kocci.healtikuy.core.data.repository

import com.kocci.healtikuy.core.data.remote.RemoteDataSource
import com.kocci.healtikuy.core.domain.repository.IAuthRepository
import javax.inject.Inject

class AuthRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    IAuthRepository {
    override fun isUserLogin(): Boolean {
        return remoteDataSource.isUserLogin()
    }
}
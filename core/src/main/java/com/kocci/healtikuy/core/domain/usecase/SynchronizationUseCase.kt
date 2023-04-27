package com.kocci.healtikuy.core.domain.usecase

import com.kocci.healtikuy.core.data.remote.model.Async
import kotlinx.coroutines.flow.Flow

interface SynchronizationUseCase {
    fun cloudSync(): Flow<Async<Unit>>
    fun localSync(): Flow<Async<Unit>>
    suspend fun syncDataIfDayChanged()
}
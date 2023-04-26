package com.kocci.healtikuy.core.domain.interactor

import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.data.repository.AppRepository
import com.kocci.healtikuy.core.domain.usecase.SynchronizationUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SynchronizationInteractor @Inject constructor(
    private val repository: AppRepository,
) : SynchronizationUseCase {
    override fun cloudSync(): Flow<Async<Unit>> {
        return repository.getRemoteDataThenSyncToLocal()
    }

    override fun localSync(): Flow<Async<Unit>> {
        return repository.getLocalDataThenSyncToCloud()
    }

    override suspend fun syncDataEveryDay() {
        repository.syncDataIfDayChanged()
    }
}
package com.kocci.healtikuy.core.domain.interactor

import com.kocci.healtikuy.core.data.repository.SleepRepository
import com.kocci.healtikuy.core.data.repository.UserPreferencesRepository
import com.kocci.healtikuy.core.domain.model.Sleep
import com.kocci.healtikuy.core.domain.usecase.SleepUseCase
import kotlinx.coroutines.flow.Flow

class SleepInteractor(
    private val repository: SleepRepository,
    private val preferencesRepository: UserPreferencesRepository
) : SleepUseCase {
    override fun setTime(time: Long) {
        TODO("Not yet implemented")
    }

    override fun getSleepData(): Flow<Sleep?> {
        TODO("Not yet implemented")
    }

    override suspend fun insertNewData(data: Sleep) {
        TODO("Not yet implemented")
    }

    override suspend fun updateNewData(data: Sleep) {
        TODO("Not yet implemented")
    }
}
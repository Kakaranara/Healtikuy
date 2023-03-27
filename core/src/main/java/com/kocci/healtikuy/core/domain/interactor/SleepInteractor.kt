package com.kocci.healtikuy.core.domain.interactor

import com.kocci.healtikuy.core.data.repository.SleepRepository
import com.kocci.healtikuy.core.data.repository.UserPreferencesRepository
import com.kocci.healtikuy.core.domain.model.Sleep
import com.kocci.healtikuy.core.domain.usecase.SleepIndicator
import com.kocci.healtikuy.core.domain.usecase.SleepUseCase
import com.kocci.healtikuy.core.util.helper.DateHelper
import com.kocci.healtikuy.core.util.mapper.toDomain
import com.kocci.healtikuy.core.util.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SleepInteractor @Inject constructor(
    private val repository: SleepRepository,
    private val preferencesRepository: UserPreferencesRepository,
) : SleepUseCase {

    override fun getSleepData(): Flow<Sleep> {
        return repository.getLatestData().map {
            if (it == null) {
                val sleep = Sleep()
                insertNewData(sleep)
                sleep
            } else if (!DateHelper.isToday(it.timeStamp)) {
                val sleep = Sleep()
                insertNewData(sleep)
                sleep
            } else {
                it.toDomain()
            }
        }
    }

    override suspend fun insertNewData(data: Sleep) {
        repository.insertData(data.toEntity())
    }

    override suspend fun updateNewData(data: Sleep) {
        repository.updateData(data.toEntity())
    }

    override fun getSetTime(): Flow<SleepIndicator> {
        return preferencesRepository.sleepTimePreference.map {
            if (it == null) {
                SleepIndicator.NotSet
            } else {
                SleepIndicator.Set(it)
            }
        }
    }

    override suspend fun changeSetTime(time: Long) {
        return preferencesRepository.changeSleepTime(time)
    }
}
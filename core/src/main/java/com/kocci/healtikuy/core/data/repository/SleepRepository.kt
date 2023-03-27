package com.kocci.healtikuy.core.data.repository

import com.kocci.healtikuy.core.data.local.LocalDataSource
import com.kocci.healtikuy.core.data.local.entity.SleepEntity
import com.kocci.healtikuy.core.domain.repository.ISleepRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SleepRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
) : ISleepRepository {
    override fun getLatestData(): Flow<SleepEntity?> {
        return localDataSource.getSleepLastRow()
    }

    override suspend fun insertData(entity: SleepEntity) {
        return localDataSource.insertSleep(entity)
    }

    override suspend fun updateData(entity: SleepEntity) {
        return localDataSource.updateSleep(entity)
    }
}
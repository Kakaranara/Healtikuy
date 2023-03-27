package com.kocci.healtikuy.core.data.repository

import com.kocci.healtikuy.core.data.local.LocalDataSource
import com.kocci.healtikuy.core.data.local.entity.SleepEntity
import com.kocci.healtikuy.core.domain.repository.ISleepRepository
import kotlinx.coroutines.flow.Flow

class SleepRepository(
    private val localDataSource: LocalDataSource,
) : ISleepRepository {
    override fun getLatestData(): Flow<SleepEntity?> {
        TODO("Not yet implemented")
    }

    override fun insertData(entity: SleepEntity) {
        TODO("Not yet implemented")
    }

    override fun updateData(entity: SleepEntity) {
        TODO("Not yet implemented")
    }
}
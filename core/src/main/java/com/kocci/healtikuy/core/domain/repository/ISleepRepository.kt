package com.kocci.healtikuy.core.domain.repository

import com.kocci.healtikuy.core.data.local.entity.SleepEntity
import kotlinx.coroutines.flow.Flow

interface ISleepRepository {
    fun getLatestData(): Flow<SleepEntity?>
    fun insertData(entity: SleepEntity)
    fun updateData(entity: SleepEntity)
}
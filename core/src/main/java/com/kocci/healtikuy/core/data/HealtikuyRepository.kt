package com.kocci.healtikuy.core.data

import com.kocci.healtikuy.core.data.local.LocalDataSource
import com.kocci.healtikuy.core.data.local.entity.WaterIntakeEntity
import javax.inject.Inject


class HealtikuyRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) {

    fun getLatestWaterIntakeData() =
        localDataSource.getWaterIntakeLastRow()

    suspend fun insertWaterIntake(entity: WaterIntakeEntity) =
        localDataSource.insertWaterIntake(entity)

    suspend fun updateWaterIntake(entity: WaterIntakeEntity) =
        localDataSource.updateWaterIntake(entity)
}
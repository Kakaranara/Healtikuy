package com.kocci.healtikuy.core.data.local

import com.kocci.healtikuy.core.data.local.db.WaterIntakeDao
import com.kocci.healtikuy.core.data.local.entity.WaterIntakeEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val waterIntakeDao: WaterIntakeDao
) {
    fun getWaterIntakeLastRow() = waterIntakeDao.selectLastRow()
    suspend fun insertWaterIntake(data: WaterIntakeEntity) = waterIntakeDao.insertNewData(data)
    suspend fun updateWaterIntake(data: WaterIntakeEntity) = waterIntakeDao.updateData(data)

}
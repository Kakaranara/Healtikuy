package com.kocci.healtikuy.core.data.local

import com.kocci.healtikuy.core.data.local.db.HealtikuyRoomDatabase
import com.kocci.healtikuy.core.data.local.db.SleepDao
import com.kocci.healtikuy.core.data.local.db.WaterIntakeDao
import com.kocci.healtikuy.core.data.local.entity.SleepEntity
import com.kocci.healtikuy.core.data.local.entity.WaterIntakeEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val database: HealtikuyRoomDatabase,
    private val waterIntakeDao: WaterIntakeDao,
    private val sleepDao: SleepDao,
) {

    /**
     * Water Intake
     */
    fun getWaterIntakeLastRow() = waterIntakeDao.selectLastRow()
    suspend fun insertWaterIntake(data: WaterIntakeEntity) = waterIntakeDao.insertNewData(data)
    suspend fun updateWaterIntake(data: WaterIntakeEntity) = waterIntakeDao.updateData(data)

    /**
     * Sleep
     */

    fun getSleepLastRow() = sleepDao.selectLastRow()
    suspend fun insertSleep(entity: SleepEntity) = sleepDao.insertNewData(entity)
    suspend fun updateSleep(entity: SleepEntity) = sleepDao.insertNewData(entity)

    fun clearDatabase() {
        database.clearAllTables()
    }
}
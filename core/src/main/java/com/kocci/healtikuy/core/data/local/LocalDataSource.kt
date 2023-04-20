package com.kocci.healtikuy.core.data.local

import com.kocci.healtikuy.core.data.local.db.HealtikuyRoomDatabase
import com.kocci.healtikuy.core.data.local.db.SleepDao
import com.kocci.healtikuy.core.data.local.db.WaterIntakeDao
import com.kocci.healtikuy.core.data.local.db.exercise.JoggingDao
import com.kocci.healtikuy.core.data.local.db.exercise.RunningDao
import com.kocci.healtikuy.core.data.local.db.exercise.StaticBikeDao
import com.kocci.healtikuy.core.data.local.entity.SleepEntity
import com.kocci.healtikuy.core.data.local.entity.WaterIntakeEntity
import com.kocci.healtikuy.core.data.local.entity.exercise.JoggingEntity
import com.kocci.healtikuy.core.data.local.entity.exercise.RunningEntity
import com.kocci.healtikuy.core.data.local.entity.exercise.StaticBikeEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val database: HealtikuyRoomDatabase,
    private val waterIntakeDao: WaterIntakeDao,
    private val sleepDao: SleepDao,
    private val joggingDao: JoggingDao,
    private val runningDao: RunningDao,
    private val staticBikeDao: StaticBikeDao
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

    /**
     * Jogging
     */

    fun getJoggingLastRow() = joggingDao.getLatestData()
    fun getAllJoggingData() = joggingDao.getAllData()
    suspend fun insertJoggingData(entity: JoggingEntity) = joggingDao.insertEntity(entity)
    suspend fun updateJoggingData(entity: JoggingEntity) = joggingDao.updateEntity(entity)

    /**
     * Running
     */

    fun getRunningLastRow() = runningDao.getLatestData()
    fun getAllRunningData() = runningDao.getAllData()
    suspend fun insertRunningData(entity: RunningEntity) = runningDao.insertEntity(entity)
    suspend fun updateRunningData(entity: RunningEntity) = runningDao.updateEntity(entity)


    /**
     * Static Bike
     */

    fun getStaticBikeLastRow() = staticBikeDao.getLatestData()
    fun getAllStaticBikeData() = staticBikeDao.getAllData()
    suspend fun insertStaticBikeData(entity: StaticBikeEntity) = staticBikeDao.insertEntity(entity)
    suspend fun updateStaticBikeData(entity: StaticBikeEntity) = staticBikeDao.updateEntity(entity)


    /**
     * ! Danger Zone
     */

    fun clearDatabase() {
        database.clearAllTables()
    }
}
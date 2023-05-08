package com.kocci.healtikuy.core.data.local

import androidx.room.Transaction
import com.kocci.healtikuy.core.data.local.db.HealtikuyDao
import com.kocci.healtikuy.core.data.local.db.HealtikuyRoomDatabase
import com.kocci.healtikuy.core.data.local.db.NutritionDao
import com.kocci.healtikuy.core.data.local.db.SleepDao
import com.kocci.healtikuy.core.data.local.db.SunExposureDao
import com.kocci.healtikuy.core.data.local.db.WaterIntakeDao
import com.kocci.healtikuy.core.data.local.db.exercise.JoggingDao
import com.kocci.healtikuy.core.data.local.db.exercise.RunningDao
import com.kocci.healtikuy.core.data.local.db.exercise.StaticBikeDao
import com.kocci.healtikuy.core.data.local.entity.ChallengeEntity
import com.kocci.healtikuy.core.data.local.entity.NutritionEntity
import com.kocci.healtikuy.core.data.local.entity.SleepEntity
import com.kocci.healtikuy.core.data.local.entity.SunExposureEntity
import com.kocci.healtikuy.core.data.local.entity.WaterIntakeEntity
import com.kocci.healtikuy.core.data.local.entity.exercise.JoggingEntity
import com.kocci.healtikuy.core.data.local.entity.exercise.RunningEntity
import com.kocci.healtikuy.core.data.local.entity.exercise.StaticBikeEntity
import com.kocci.healtikuy.core.util.helper.DateHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val database: HealtikuyRoomDatabase,
    private val healtikuyDao: HealtikuyDao,
    private val waterIntakeDao: WaterIntakeDao,
    private val sleepDao: SleepDao,
    private val joggingDao: JoggingDao,
    private val runningDao: RunningDao,
    private val staticBikeDao: StaticBikeDao,
    private val nutritionDao: NutritionDao,
    private val sunExposureDao: SunExposureDao
) {

    /**
     * Water Intake
     */
    fun getWaterIntakeLastRow() = waterIntakeDao.selectLastRow()
    suspend fun insertWaterIntake(data: WaterIntakeEntity) = waterIntakeDao.insertNewData(data)
    suspend fun updateWaterIntake(data: WaterIntakeEntity) = waterIntakeDao.updateData(data)
    suspend fun clearWaterIntake() = waterIntakeDao.deleteTables()

    /**
     * Sleep
     */

    fun getSleepLastRow() = sleepDao.selectLastRow()
    suspend fun insertSleep(entity: SleepEntity) = sleepDao.insertNewData(entity)
    suspend fun updateSleep(entity: SleepEntity) = sleepDao.insertNewData(entity)
    suspend fun getAllData() = sleepDao.getAllData()
    suspend fun clearSleep() = sleepDao.deleteTables()

    /**
     * Sun Exposure
     */

    fun getSunExposureLastRow() = sunExposureDao.selectLastRow()
    suspend fun insertSunExposure(entity: SunExposureEntity) = sunExposureDao.insertNewData(entity)
    suspend fun updateSunExposure(entity: SunExposureEntity) = sunExposureDao.updateData(entity)
    suspend fun getAllSunExposureData() = sunExposureDao.getAllData()
    suspend fun clearSunExposure() = sunExposureDao.deleteTables()

    /**
     * Jogging
     */

    fun getJoggingLastRow() = joggingDao.getLatestData()
    suspend fun getAllJoggingData() = joggingDao.getAllData()
    suspend fun insertJoggingData(entity: JoggingEntity) = joggingDao.insertEntity(entity)
    suspend fun updateJoggingData(entity: JoggingEntity) = joggingDao.updateEntity(entity)
    suspend fun clearJogging() = joggingDao.deleteTables()

    /**
     * Running
     */

    fun getRunningLastRow() = runningDao.getLatestData()
    suspend fun getAllRunningData() = runningDao.getAllData()
    suspend fun insertRunningData(entity: RunningEntity) = runningDao.insertEntity(entity)
    suspend fun updateRunningData(entity: RunningEntity) = runningDao.updateEntity(entity)
    suspend fun clearRunning() = runningDao.deleteTables()


    /**
     * Static Bike
     */

    fun getStaticBikeLastRow() = staticBikeDao.getLatestData()
    suspend fun getAllStaticBikeData() = staticBikeDao.getAllData()
    suspend fun insertStaticBikeData(entity: StaticBikeEntity) = staticBikeDao.insertEntity(entity)
    suspend fun updateStaticBikeData(entity: StaticBikeEntity) = staticBikeDao.updateEntity(entity)
    suspend fun clearStaticBike() = staticBikeDao.deleteTables()

    /**
     * Nutrition Related
     */

    fun getNutritionData() = nutritionDao.selectTodayData(DateHelper.getUnixEpoch())
    suspend fun insertNutritionData(entity: NutritionEntity) = nutritionDao.insert(entity)
    suspend fun getAllNutritionData() = nutritionDao.getAllData()
    suspend fun clearNutrition() = nutritionDao.deleteTables()

    /**
     * Home related
     */

    //? Challenges aren't used because for now we use remote database (could be used later)
    fun getChallengesData() = healtikuyDao.getChallengeProgress()
    fun updateChallenges(entity: ChallengeEntity) = healtikuyDao.update(entity)
    fun insertChallenges(entity: ChallengeEntity) = healtikuyDao.insert(entity)

    /**
     * ! Danger Zone
     */

    //? Clear all tables. Used when we logout.
    @Transaction
    suspend fun clearDatabase() {
        //write it in sequentially like the main feature in home.
        clearWaterIntake()
        clearSleep()
        clearJogging()
        clearRunning()
        clearStaticBike()
        clearNutrition()
        clearSunExposure()
//            database.clearAllTables() //literally clear all tables but also remove pre-populated tables
    }
}
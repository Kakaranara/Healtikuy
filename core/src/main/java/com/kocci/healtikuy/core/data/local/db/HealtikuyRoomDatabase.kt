package com.kocci.healtikuy.core.data.local.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kocci.healtikuy.core.data.local.db.exercise.JoggingDao
import com.kocci.healtikuy.core.data.local.db.exercise.RunningDao
import com.kocci.healtikuy.core.data.local.db.exercise.StaticBikeDao
import com.kocci.healtikuy.core.data.local.entity.AvoidFeatureEntity
import com.kocci.healtikuy.core.data.local.entity.ChallengeEntity
import com.kocci.healtikuy.core.data.local.entity.NutritionEntity
import com.kocci.healtikuy.core.data.local.entity.SleepEntity
import com.kocci.healtikuy.core.data.local.entity.SunExposureEntity
import com.kocci.healtikuy.core.data.local.entity.WaterIntakeEntity
import com.kocci.healtikuy.core.data.local.entity.exercise.JoggingEntity
import com.kocci.healtikuy.core.data.local.entity.exercise.RunningEntity
import com.kocci.healtikuy.core.data.local.entity.exercise.StaticBikeEntity

@Database(
    entities = [
        WaterIntakeEntity::class,
        SleepEntity::class,
        JoggingEntity::class,
        RunningEntity::class,
        StaticBikeEntity::class,
        ChallengeEntity::class,
        NutritionEntity::class,
        SunExposureEntity::class,
        AvoidFeatureEntity::class
    ],
    version = 2,
    exportSchema = true,
)
abstract class HealtikuyRoomDatabase : RoomDatabase() {



    abstract fun dao(): HealtikuyDao
    abstract fun waterDao(): WaterIntakeDao
    abstract fun sleepDao(): SleepDao
    abstract fun joggingDao(): JoggingDao
    abstract fun runningDao(): RunningDao
    abstract fun staticBikeDao(): StaticBikeDao
    abstract fun nutritionDao(): NutritionDao
    abstract fun sunExposureDao(): SunExposureDao
    abstract fun avoidFeatureDao(): AvoidFeatureDao
}
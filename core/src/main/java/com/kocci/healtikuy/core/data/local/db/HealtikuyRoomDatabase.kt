package com.kocci.healtikuy.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kocci.healtikuy.core.data.local.db.exercise.JoggingDao
import com.kocci.healtikuy.core.data.local.db.exercise.RunningDao
import com.kocci.healtikuy.core.data.local.db.exercise.StaticBikeDao
import com.kocci.healtikuy.core.data.local.entity.ChallengeEntity
import com.kocci.healtikuy.core.data.local.entity.SleepEntity
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
        ChallengeEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class HealtikuyRoomDatabase : RoomDatabase() {
    abstract fun dao(): HealtikuyDao
    abstract fun waterDao(): WaterIntakeDao
    abstract fun sleepDao(): SleepDao
    abstract fun joggingDao(): JoggingDao
    abstract fun runningDao(): RunningDao
    abstract fun staticBikeDao(): StaticBikeDao
}
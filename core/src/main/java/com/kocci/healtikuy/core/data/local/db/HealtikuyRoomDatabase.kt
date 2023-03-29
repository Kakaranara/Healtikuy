package com.kocci.healtikuy.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kocci.healtikuy.core.data.local.entity.SleepEntity
import com.kocci.healtikuy.core.data.local.entity.WaterIntakeEntity

@Database(
    entities = [WaterIntakeEntity::class, SleepEntity::class],
    version = 1,
    exportSchema = false
)
abstract class HealtikuyRoomDatabase : RoomDatabase() {
    abstract fun dao(): HealtikuyDao
    abstract fun waterDao(): WaterIntakeDao
    abstract fun sleepDao(): SleepDao
}
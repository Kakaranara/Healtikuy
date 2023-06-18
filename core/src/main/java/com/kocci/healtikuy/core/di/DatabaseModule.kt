package com.kocci.healtikuy.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kocci.healtikuy.core.data.local.db.AvoidFeatureDao
import com.kocci.healtikuy.core.data.local.db.HealtikuyDao
import com.kocci.healtikuy.core.data.local.db.HealtikuyRoomDatabase
import com.kocci.healtikuy.core.data.local.db.NutritionDao
import com.kocci.healtikuy.core.data.local.db.SleepDao
import com.kocci.healtikuy.core.data.local.db.SunExposureDao
import com.kocci.healtikuy.core.data.local.db.WaterIntakeDao
import com.kocci.healtikuy.core.data.local.db.exercise.JoggingDao
import com.kocci.healtikuy.core.data.local.db.exercise.RunningDao
import com.kocci.healtikuy.core.data.local.db.exercise.StaticBikeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `avoid_feature` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `alcohol` INTEGER NOT NULL, `smoke` INTEGER NOT NULL, `limitSugar` INTEGER NOT NULL, `limitFat` INTEGER NOT NULL, `timeStamp` INTEGER NOT NULL)")
        }
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): HealtikuyRoomDatabase {
        return Room.databaseBuilder(
            context,
            HealtikuyRoomDatabase::class.java,
            "healtikuy.db"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides
    @Singleton
    fun provideWaterIntakeDao(database: HealtikuyRoomDatabase): WaterIntakeDao {
        return database.waterDao()
    }

    @Provides
    @Singleton
    fun provideSleepDao(database: HealtikuyRoomDatabase): SleepDao {
        return database.sleepDao()
    }

    @Provides
    @Singleton
    fun provideJoggingDao(database: HealtikuyRoomDatabase): JoggingDao {
        return database.joggingDao()
    }

    @Provides
    @Singleton
    fun provideRunningDao(database: HealtikuyRoomDatabase): RunningDao {
        return database.runningDao()
    }

    @Provides
    @Singleton
    fun provideStaticBikeDao(database: HealtikuyRoomDatabase): StaticBikeDao {
        return database.staticBikeDao()
    }

    @Provides
    @Singleton
    fun provideHealtikuyDao(database: HealtikuyRoomDatabase): HealtikuyDao {
        return database.dao()
    }

    @Provides
    @Singleton
    fun provideNutritionDao(database: HealtikuyRoomDatabase): NutritionDao {
        return database.nutritionDao()
    }

    @Provides
    @Singleton
    fun provideSunexposureDao(database: HealtikuyRoomDatabase): SunExposureDao {
        return database.sunExposureDao()
    }

    @Provides
    @Singleton
    fun provideAvoidFeatureDao(database: HealtikuyRoomDatabase): AvoidFeatureDao {
        return database.avoidFeatureDao()
    }

}
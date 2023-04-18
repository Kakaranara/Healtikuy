package com.kocci.healtikuy.core.di

import android.content.Context
import androidx.room.Room
import com.kocci.healtikuy.core.data.local.db.HealtikuyRoomDatabase
import com.kocci.healtikuy.core.data.local.db.SleepDao
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

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): HealtikuyRoomDatabase {
        return Room.databaseBuilder(
            context,
            HealtikuyRoomDatabase::class.java,
            "healtikuy.db"
        ).build()
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


}
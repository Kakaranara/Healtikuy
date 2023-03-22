package com.kocci.healtikuy.core.di

import android.content.Context
import androidx.room.Room
import com.kocci.healtikuy.core.data.local.db.HealtikuyRoomDatabase
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
}
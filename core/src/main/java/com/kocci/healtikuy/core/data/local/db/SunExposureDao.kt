package com.kocci.healtikuy.core.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kocci.healtikuy.core.data.local.entity.SunExposureEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SunExposureDao {
    @Query("SELECT * FROM sun_exposure ORDER BY id DESC LIMIT 1")
    fun selectLastRow(): Flow<SunExposureEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewData(entity: SunExposureEntity)

    @Update
    suspend fun updateData(entity: SunExposureEntity)

    @Query("SELECT * FROM sun_exposure")
    suspend fun getAllData(): List<SunExposureEntity>
}
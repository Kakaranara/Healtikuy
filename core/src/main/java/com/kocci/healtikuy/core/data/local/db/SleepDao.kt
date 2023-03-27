package com.kocci.healtikuy.core.data.local.db

import androidx.room.*
import com.kocci.healtikuy.core.data.local.entity.SleepEntity
import com.kocci.healtikuy.core.data.local.entity.WaterIntakeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepDao {
    @Query("SELECT * FROM WaterIntake ORDER BY id DESC LIMIT 1")
    fun selectLastRow(): Flow<SleepEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewData(sleepEntity: SleepEntity)

    @Update
    suspend fun updateData(sleepEntity: SleepEntity)
}
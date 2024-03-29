package com.kocci.healtikuy.core.data.local.db

import androidx.room.*
import com.kocci.healtikuy.core.data.local.entity.WaterIntakeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterIntakeDao {
    @Query("SELECT * FROM water_intake ORDER BY id DESC LIMIT 1")
    fun selectLastRow(): Flow<WaterIntakeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewData(waterIntake: WaterIntakeEntity)

    @Update
    suspend fun updateData(waterIntake: WaterIntakeEntity)

    @Query("DELETE FROM water_intake")
    suspend fun deleteTables()

}
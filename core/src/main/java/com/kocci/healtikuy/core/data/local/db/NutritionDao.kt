package com.kocci.healtikuy.core.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kocci.healtikuy.core.data.local.entity.NutritionEntity
import com.kocci.healtikuy.core.util.helper.DateHelper
import kotlinx.coroutines.flow.Flow

@Dao
interface NutritionDao {
    @Query(
        """
        SELECT *, DATE(unixTimestamp, 'unixepoch') as tanggal
        FROM nutrition
        WHERE tanggal = DATE(:unixEpoch,'unixepoch')
    """
    )
    fun selectTodayData(unixEpoch: Long = DateHelper.getUnixEpoch()): Flow<List<NutritionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: NutritionEntity)

    @Query("SELECT * FROM nutrition")
    suspend fun getAllData(): List<NutritionEntity>

    @Query("DELETE FROM nutrition")
    suspend fun deleteTables()
}
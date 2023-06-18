package com.kocci.healtikuy.core.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kocci.healtikuy.core.data.local.entity.AvoidFeatureEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AvoidFeatureDao {
    @Query("SELECT * from avoid_feature ORDER BY timeStamp DESC LIMIT 1")
    fun getLatestData(): Flow<AvoidFeatureEntity?>

    @Update
    suspend fun changeData(data: AvoidFeatureEntity)

    @Insert
    suspend fun insertNewData(data: AvoidFeatureEntity)
}
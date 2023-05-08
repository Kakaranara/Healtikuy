package com.kocci.healtikuy.core.data.local.db.exercise

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kocci.healtikuy.core.data.local.entity.exercise.StaticBikeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StaticBikeDao {
    @Query("SELECT * FROM static_bike")
    suspend fun getAllData(): List<StaticBikeEntity>

    @Query("SELECT * FROM static_bike ORDER BY id DESC LIMIT 1")
    fun getLatestData(): Flow<StaticBikeEntity?>

    @Update
    suspend fun updateEntity(entity: StaticBikeEntity)

    @Insert
    suspend fun insertEntity(entity: StaticBikeEntity)


    @Query("DELETE FROM static_bike")
    suspend fun deleteTables()
}
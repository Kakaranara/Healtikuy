package com.kocci.healtikuy.core.data.local.db.exercise

import androidx.room.*
import com.kocci.healtikuy.core.data.local.entity.exercise.JoggingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JoggingDao {
    @Query("SELECT * FROM jogging")
    fun getAllData(): List<JoggingEntity>

    @Query("SELECT * FROM jogging ORDER BY id DESC LIMIT 1")
    fun getLatestData(): Flow<JoggingEntity?>

    @Update
    suspend fun updateEntity(entity: JoggingEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntity(entity: JoggingEntity)
}
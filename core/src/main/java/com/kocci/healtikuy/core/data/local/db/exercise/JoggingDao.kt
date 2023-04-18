package com.kocci.healtikuy.core.data.local.db.exercise

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kocci.healtikuy.core.data.local.entity.exercise.JoggingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JoggingDao {
    @Query("SELECT * FROM jogging")
    fun getAllData(): List<JoggingEntity>

    @Query("SELECT * FROM jogging ORDER BY id DESC LIMIT 1")
    fun getLatestData(): Flow<JoggingEntity?>

    @Update
    fun updateEntity(entity: JoggingEntity)

    @Insert
    fun insertEntity(entity: JoggingEntity)
}
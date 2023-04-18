package com.kocci.healtikuy.core.data.local.db.exercise

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kocci.healtikuy.core.data.local.entity.exercise.RunningEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RunningDao {
    @Query("SELECT * FROM running")
    fun getAllData() : List<RunningEntity>

    @Query("SELECT * FROM running ORDER BY id DESC LIMIT 1")
    fun getLatestData(): Flow<RunningEntity?>

    @Update
    fun updateEntity(entity: RunningEntity)

    @Insert
    fun insertEntity(entity: RunningEntity)
}
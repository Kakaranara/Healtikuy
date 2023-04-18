package com.kocci.healtikuy.core.data.local.db.exercise

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kocci.healtikuy.core.data.local.entity.exercise.StaticBikeEntity

@Dao
interface StaticBikeDao {
    @Query("SELECT * FROM static_bike")
    fun getAllData(): List<StaticBikeEntity>

    @Query("SELECT * FROM static_bike ORDER BY id DESC LIMIT 1")
    fun getLatestData(): StaticBikeEntity?

    @Update
    fun updateEntity(entity: StaticBikeEntity)

    @Insert
    fun insertEntity(entity: StaticBikeEntity)
}
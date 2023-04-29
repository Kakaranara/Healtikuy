package com.kocci.healtikuy.core.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kocci.healtikuy.core.data.local.entity.ChallengeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HealtikuyDao {
    @Query("SELECT * FROM challenges")
    fun getChallengeProgress(): Flow<List<ChallengeEntity>>

    @Update
    fun update(entity: ChallengeEntity)

    @Insert
    fun insert(entity: ChallengeEntity)

}
package com.kocci.healtikuy.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("challenges")
data class ChallengeEntity(
    @PrimaryKey(true)
    val id: Int = 0,
    val name: String,
    val category: String,
    val isCompleted: Boolean = false,
    val coinRewards: Int
)
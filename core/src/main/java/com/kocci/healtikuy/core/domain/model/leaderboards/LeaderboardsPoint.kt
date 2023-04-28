package com.kocci.healtikuy.core.domain.model.leaderboards

data class LeaderboardsPoint(
    val name: String,
    val avatar: String,
    val points: Long,
    val position: Int,
)
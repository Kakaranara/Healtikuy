package com.kocci.healtikuy.core.domain.model.leaderboards

data class LeaderboardsPoint(
    val name: String,
    val avatar: String,
    val points: Long,
    val position: Int,
    val running100MPoints: Long,
    val running200MPoints: Long,
    val running400MPoints: Long,
)
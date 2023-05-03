package com.kocci.healtikuy.core.domain.model.leaderboards

data class LeaderboardsAttr(
    val name: String,
    val avatar: String,
    val points: Long,
    val running100MPoints: Long,
    val running200MPoints: Long,
    val running400MPoints: Long,
)
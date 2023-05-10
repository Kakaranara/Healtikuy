package com.kocci.healtikuy.core.domain.usecase

import com.kocci.healtikuy.core.constant.RunningType
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.leaderboards.LeaderboardsAttr
import com.kocci.healtikuy.core.domain.model.leaderboards.LeaderboardsPoint
import kotlinx.coroutines.flow.Flow


interface LeaderboardsUseCase {
    fun getLeaderboardPoint(): Flow<Async<List<LeaderboardsAttr>>>
    fun getLeaderboardRunning(
        runningType: RunningType
    ): Flow<Async<List<LeaderboardsAttr>>>

    fun mapToPoints(list: List<LeaderboardsAttr>): List<LeaderboardsPoint>
    fun mapToRunningPoints(
        list: List<LeaderboardsAttr>,
        runningType: RunningType
    ): List<LeaderboardsPoint>
}
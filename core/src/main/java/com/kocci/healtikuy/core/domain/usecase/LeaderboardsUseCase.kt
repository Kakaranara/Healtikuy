package com.kocci.healtikuy.core.domain.usecase

import com.kocci.healtikuy.core.constant.RunningType
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.leaderboards.LeaderboardsAttr
import com.kocci.healtikuy.core.domain.model.leaderboards.LeaderboardsPoint
import kotlinx.coroutines.flow.Flow

/**
 * For running leaderboards, a smallest time is considered as a point.
 * But, a nonsense time such as 0,1,2,3,4 second to reach a 100meter short distance run,
 * is prohibited. even usain bolt, need 9 second for it. So, 0 - 8 second cannot be considered as input.
 * (validate in fragments)
 */
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
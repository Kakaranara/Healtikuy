package com.kocci.healtikuy.core.domain.usecase

import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.leaderboards.LeaderboardsAttr
import kotlinx.coroutines.flow.Flow


interface LeaderboardsUseCase {
    fun getLeaderboardsData(): Flow<Async<List<LeaderboardsAttr>>>
}
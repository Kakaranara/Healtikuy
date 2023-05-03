package com.kocci.healtikuy.core.domain.repository

import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.model.leaderboards.LeaderboardsAttr
import kotlinx.coroutines.flow.Flow

interface ILeaderboardsRepository {
    fun getLeaderboards(): Flow<Async<List<LeaderboardsAttr>>>
}
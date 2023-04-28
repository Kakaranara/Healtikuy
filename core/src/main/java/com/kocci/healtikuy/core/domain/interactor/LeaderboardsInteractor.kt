package com.kocci.healtikuy.core.domain.interactor

import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.data.repository.LeaderboardsRepository
import com.kocci.healtikuy.core.domain.model.leaderboards.LeaderboardsAttr
import com.kocci.healtikuy.core.domain.usecase.LeaderboardsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LeaderboardsInteractor @Inject constructor(
    private val repository: LeaderboardsRepository
) : LeaderboardsUseCase {
    override fun getLeaderboardsData(): Flow<Async<List<LeaderboardsAttr>>> {
        return repository.getLeaderboards()
    }
}
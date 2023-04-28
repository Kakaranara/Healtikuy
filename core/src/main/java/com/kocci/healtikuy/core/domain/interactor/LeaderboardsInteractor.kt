package com.kocci.healtikuy.core.domain.interactor

import com.kocci.healtikuy.core.constant.RunningType
import com.kocci.healtikuy.core.constant.RunningType.*
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.data.repository.LeaderboardsRepository
import com.kocci.healtikuy.core.domain.model.leaderboards.LeaderboardsAttr
import com.kocci.healtikuy.core.domain.model.leaderboards.LeaderboardsPoint
import com.kocci.healtikuy.core.domain.usecase.leaderboards.LeaderboardsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LeaderboardsInteractor @Inject constructor(
    private val repository: LeaderboardsRepository
) : LeaderboardsUseCase {
    override fun getLeaderboardsData(): Flow<Async<List<LeaderboardsAttr>>> {
        return repository.getLeaderboards()
    }

    override fun sortByPoints(list: List<LeaderboardsAttr>): List<LeaderboardsPoint> {
        return list.sortedByDescending { it.points }.mapIndexed { i, it ->
            LeaderboardsPoint(it.name, it.avatar, it.points, i + 1)
        }
    }

    override fun sortByRunningPoints(
        list: List<LeaderboardsAttr>,
        runningType: RunningType
    ): List<LeaderboardsPoint> {
        return when (runningType) {
            Running100M -> {
                list.sortedByDescending { it.running100MPoints }.mapIndexed { i, it ->
                    LeaderboardsPoint(
                        it.name,
                        it.avatar,
                        it.running100MPoints,
                        i + 1
                    )
                }
            }

            Running200M -> {
                list.sortedByDescending { it.running200MPoints }.mapIndexed { i, it ->
                    LeaderboardsPoint(
                        it.name,
                        it.avatar,
                        it.running200MPoints,
                        i + 1
                    )
                }
            }

            Running400M -> {
                list.sortedByDescending { it.running400MPoints }.mapIndexed { i, it ->
                    LeaderboardsPoint(
                        it.name,
                        it.avatar,
                        it.running400MPoints,
                        i + 1
                    )
                }
            }
        }

    }
}
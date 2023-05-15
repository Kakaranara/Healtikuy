package com.kocci.healtikuy.ui.main.leaderboards.running

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kocci.healtikuy.core.constant.RunningType
import com.kocci.healtikuy.core.domain.model.leaderboards.LeaderboardsAttr
import com.kocci.healtikuy.core.domain.usecase.LeaderboardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RunningLeadVM @Inject constructor(
    private val leaderboardsUseCase: LeaderboardsUseCase
) : ViewModel() {

    fun getLeaderboardData(runningType: RunningType) = leaderboardsUseCase.getLeaderboardRunning(
        runningType
    ).asLiveData()

    fun mapToLeaderPoints(list: List<LeaderboardsAttr>, runningType: RunningType) =
        leaderboardsUseCase.mapToRunningPoints(list, runningType)

}